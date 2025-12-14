# Test Configuration - RiskLocator

## Current Test Setup

This document describes the testing infrastructure that is already configured in this project.

## Test Dependencies

The following test dependencies are configured in `app/build.gradle.kts`:

### Unit Testing
- **JUnit 4.13.2**: Standard testing framework for Java/Kotlin
  - Used for: Logic testing without Android framework dependencies
  - Runs on: Local JVM (fast execution)

### Instrumented Testing
- **AndroidX Test JUnit 1.2.1**: JUnit integration for Android
  - Used for: Running JUnit tests on Android devices/emulators
  
- **Espresso Core 3.6.1**: UI testing framework
  - Used for: Testing user interactions and UI components
  - Runs on: Android devices/emulators

## Test Files

### 1. Unit Tests
**Location**: `app/src/test/java/com/example/risklocator/ExampleUnitTest.kt`

```kotlin
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
```

**Purpose**: Template for unit tests. This test verifies basic arithmetic and serves as a starting point for adding more unit tests.

### 2. Instrumented Tests
**Location**: `app/src/androidTest/java/com/example/risklocator/ExampleInstrumentedTest.kt`

```kotlin
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.risklocator", appContext.packageName)
    }
}
```

**Purpose**: Template for instrumented tests. This test verifies the app context and package name, ensuring the test infrastructure is working correctly.

## Test Configuration

### In `app/build.gradle.kts`:

```kotlin
android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
```

### Version Catalog (`gradle/libs.versions.toml`):

```toml
[versions]
junit = "4.13.2"
junitVersion = "1.2.1"
espressoCore = "3.6.1"

[libraries]
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
```

## Gradle Commands

### Unit Tests
```bash
# Run all unit tests
./gradlew test

# Run unit tests for debug variant
./gradlew testDebugUnitTest

# Run unit tests for release variant
./gradlew testReleaseUnitTest

# Run tests with detailed output
./gradlew test --info
```

### Instrumented Tests
```bash
# Run all instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Run instrumented tests for debug variant
./gradlew connectedDebugAndroidTest

# Run specific test class
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.example.risklocator.ExampleInstrumentedTest
```

### Combined
```bash
# Run all tests (unit + instrumented)
./gradlew test connectedAndroidTest

# Clean and test
./gradlew clean test
```

## Test Reports

After running tests, HTML reports are generated:

### Unit Test Reports
- **Location**: `app/build/reports/tests/testDebugUnitTest/index.html`
- **Contains**: Test results, execution time, pass/fail status

### Instrumented Test Reports
- **Location**: `app/build/reports/androidTests/connected/index.html`
- **Contains**: Device info, test results, screenshots (if configured)

## Adding New Tests

### Adding a Unit Test

1. Create a new Kotlin file in `app/src/test/java/com/example/risklocator/`
2. Add test class and methods:

```kotlin
import org.junit.Test
import org.junit.Assert.*

class MyFeatureTest {
    @Test
    fun testFeature() {
        // Arrange
        val input = "test"
        
        // Act
        val result = input.uppercase()
        
        // Assert
        assertEquals("TEST", result)
    }
}
```

### Adding an Instrumented Test

1. Create a new Kotlin file in `app/src/androidTest/java/com/example/risklocator/`
2. Add test class with AndroidJUnit4 runner:

```kotlin
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class MyInstrumentedTest {
    @Test
    fun testAndroidFeature() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertNotNull(context)
    }
}
```

## Test Annotations

Common JUnit annotations used in tests:

- `@Test`: Marks a method as a test method
- `@Before`: Runs before each test method
- `@After`: Runs after each test method
- `@BeforeClass`: Runs once before all tests (must be static)
- `@AfterClass`: Runs once after all tests (must be static)
- `@Ignore`: Temporarily disable a test
- `@RunWith`: Specifies the test runner

Example:
```kotlin
class ExampleTest {
    @Before
    fun setup() {
        // Initialize test data
    }
    
    @Test
    fun testSomething() {
        // Test code
    }
    
    @After
    fun tearDown() {
        // Clean up
    }
}
```

## Common Assertions

JUnit provides various assertion methods:

```kotlin
// Equality
assertEquals(expected, actual)
assertNotEquals(notExpected, actual)

// Nullability
assertNull(object)
assertNotNull(object)

// Boolean
assertTrue(condition)
assertFalse(condition)

// Array equality
assertArrayEquals(expectedArray, actualArray)

// Same object reference
assertSame(expected, actual)
assertNotSame(expected, actual)

// Exceptions
assertThrows(Exception::class.java) {
    // Code that should throw exception
}
```

## CI/CD Integration

To integrate tests in a CI/CD pipeline:

### GitHub Actions Example

```yaml
name: Android CI

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v2
    
    - name: Set up JDK 11
      uses: actions/setup-java@v2
      with:
        java-version: '11'
        distribution: 'adopt'
    
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
    
    - name: Run unit tests
      run: ./gradlew test
    
    - name: Upload test reports
      uses: actions/upload-artifact@v2
      if: always()
      with:
        name: test-reports
        path: app/build/reports/tests/
```

## Next Steps

To expand testing in this project:

1. **Add more unit tests** for business logic
2. **Add UI tests** using Espresso for user flows
3. **Set up test coverage** with JaCoCo
4. **Configure CI/CD** to run tests automatically
5. **Add integration tests** for Firebase services (using Firebase Test Lab)
6. **Consider adding** MockK for mocking in tests

## Resources

- [Android Testing Documentation](https://developer.android.com/training/testing)
- [JUnit 4 Wiki](https://github.com/junit-team/junit4/wiki)
- [Espresso Documentation](https://developer.android.com/training/testing/espresso)
- [AndroidX Test](https://developer.android.com/training/testing/set-up-project)
