# DaggerRxMVVMSample
This project demostrate the use of Dagger with RxJava, MVVM and unit test concepts together.

## Dagger setup steps in Android

### STEP 1
* Add the latest dagger dependancy.
```
    // Dagger version
    def dagger_version = "2.23.2"
    
    // Dagger2 core
    implementation "com.google.dagger:dagger:$dagger_version"
    annotationProcessor "com.google.dagger:dagger-compiler:$dagger_version"
    
    // Dagger Android
    implementation "com.google.dagger:dagger-android:$dagger_version"
    implementation "com.google.dagger:dagger-android-support:$dagger_version"
    
    //Dagger Annotation Processor
    annotationProcessor "com.google.dagger:dagger-android-processor:$dagger_version"
    
    //Dagger Kotlin
    kapt "com.google.dagger:dagger-compiler:$dagger_version"
    kapt "com.google.dagger:dagger-android-processor:$dagger_version"
```
* Add the following line on top of your build.gradle (Module: app) if it is not already there
```
apply plugin: 'kotlin-kapt'
```
* Sync your project

### STEP 2
* Create a BaseApplication class extending the DaggerApplication class in the root package
* Override the applicationInjector() method and return null as below
```
class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
      return null as AndroidInjector<out DaggerApplication>
    }
```
* Go to your manifest and add the following line under the application tag
```
<application
        android:name="BaseApplication"
        ...
        >
        ...
        ...
        </application>
```

### STEP 3
* Create an interface AppComponent extending AndroidInjector<BaseApplication>
* Annotate this class with @Singleton (for the whole application) & @Component (dagger component)
* Add AndroidSupportInjectionModule.class as a module of the component
* Add an interface Builder under the AppComponent and annotate it with @Component.Builder
* Add abstract methods (1. application method with param Application & returns the Builder itselt 2. Build method which returns AppComponent) under the Builder interface
```
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}
```
### STEP 4
* Now Rebuild the project to generate DaggerAppComponent
* Replace the return statement of BaseApplication to like below...
```
class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
```
* If everything is right, there should be no error till now.

### STEP 5
* Now change the AppCompatActivity to DaggerAppCompatActivity which is being extended by your MainActivity. Any Activity to use Dagger DI, we need to do this...
```
class MainActivity : DaggerAppCompatActivity() {

   ...
}
```

### STEP 6
* Create a ActivityBuildersModule class annotated with @Module
* Add abstract (static for Java | internal for Kotlin) method contributeMainActivity() that will return the MainActivity in that class
* annotate this method with @ContributesAndroidInjector like below...
```
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}
```
* Add this module to the AppComponent like this...
```
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    ...
}
```

### STEP 7
* Create Another module class called AppModule, this will be responsible for all the DI in your application
* Add a sample String DI provider by adding this method under that module class...
```
@Module
class AppModule {
    @Singleton
    @Provides
    internal fun provideSampleString(): String {
        return "xyz"
    }
    
    ...
}
```
* Again add this module to your AppComponent like this...
```
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    ...
}
```

### STEP 8
* Test your String DI by injecting the sample String to your MainActivity field and use it in your onCreate() method
```
class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var sampleText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        if (::sampleText.isInitialized)
            Toast.makeText(this, "sampleText==> $sampleText", Toast.LENGTH_LONG).show()
    }
}
```
* Now Run your application and check the Toast is being shown.





## Using Dagger in Custom Plain class
In this section we will see how to implement DI in a custom class by using Field Injection and Constructor Injection

### SECTION 1 - Create custom classes
Here we will see how to inject other DI into a field variable

* Create a plain class like below...
```
class CustomClass {
    @Inject
    lateinit var testString: String

    fun getSampleString(): String{
        return testString
    }
}
```
* Here _testString_ is injecting from the String provider written in the AppModule DI
* Provide your CustomClass in your AppModule (or any module, just make sure the module must be mentioned in the AppComponent/Any-Component) like this...
```
    @Singleton
    @Provides
    internal fun provideTestClass(testString: String): CustomClass {
        val test = CustomClass()
        test.testString = testString
        return test
    }
```
* Here the String parameter will be provided by the DI automatically as the String provider is already defined in the moduile.
* In this provider method, at first we create an object of the CustomClass and then initialize the testString field-variable and return the object.
* We can test this custom class DI in MainActivity like this...
```
    @Inject
    lateinit var customClass: CustomClass
    
    ...
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        ...
        
        if (::customClass.isInitialized)
            Toast.makeText(
                this,
                "sampleText==> ${customClass.getSampleString()}",
                Toast.LENGTH_LONG).show()
                
        ...
        
    }
```


### SECTION 2 - Create another custom classes which is dependant of the above custom class
Here we will see how to inject other DI into the Constructor

* Create a DependantCustomClass like below...
```
class DependantCustomClass @Inject constructor(private val customClass: CustomClass) {
    fun getCustomClassObject(): CustomClass{
        return customClass
    }
}
```
* Here _@Inject_ annotation to a constructor will inject all the constructor parameters by DI
* For providing the DI use following in your module...
```
    @Singleton
    @Provides
    internal fun provideDependantTestClass(customClass: CustomClass): DependantCustomClass {
        return DependantCustomClass(customClass)
    }
```
* Here the CustomClass parameter will be provided by the DI automatically because we have already written the provide method of CustomClass in the module
* We can test this custom class DI in MainActivity like this...
```
    @Inject
    lateinit var dependantCustomClass: DependantCustomClass

    override fun onCreate(savedInstanceState: Bundle?) {
        
        ...
    
        if (::dependantCustomClass.isInitialized)
            Toast.makeText(
                this,
                "sampleText==> ${dependantCustomClass.getCustomClassObject().getSampleString()}",
                Toast.LENGTH_LONG).show()
        
        ...
        
    }
```

* __SOME ADDITIONAL GUIDE__: 
You may create a separate package (named di) for collecting all the related classes/files of DI. Please check the project package-structure for more details.




