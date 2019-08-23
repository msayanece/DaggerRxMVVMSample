# DaggerRxMVVMSample
This project demostrate the use of Dagger with RxJava, MVVM and unit test concepts together.

## Dagger setup steps in Android

### STEP 1
Add the latest dagger dependancy.
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

### STEP 2
* Create a BaseApplication class extending the DaggerApplication class.
* Override the applicationInjector() method and return null
```
class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
      return null as AndroidInjector<out DaggerApplication>
    }
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




