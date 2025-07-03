# DiagnosticAppSoong


## 🛠️ Integrating `DiagnosticApp` into the AOSP Build

To include `DiagnosticApp` in your AOSP image, follow these steps:

#### 1. **Add the App to `PRODUCT_PACKAGES`**

Edit the file:

```
vendor/iti/aosp_rpi4_car_iti.mk
```

Add the following line:

```make
PRODUCT_PACKAGES += DiagnosticApp
```

This ensures that the `DiagnosticApp` is built and included in the system image.

---

#### 2. **Include the Product Configuration in Your Main Product Makefile**

Edit the main product definition file:

```
device/brcm/rpi/aosp_rpi4_car.mk
```

Append the following line to inherit the configuration from the custom product makefile:

```make
$(call inherit-product, vendor/iti/aosp_rpi4_car_iti.mk)
```

---

By performing these steps, you register `DiagnosticApp` with the build system and ensure it's packaged in the final system image for your AOSP build on Raspberry Pi 4.

---

# 📱 DiagnosticApp as AOSP Launcher Setup Guide


## 1.  AndroidManifest.xml

Declare your `MainActivity` with launcher and home intent filters:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.diagnostic_android_app">

    <application
        android:label="DiagnosticApp"
        android:theme="@android:style/Theme.DeviceDefault">

        <activity android:name=".MainActivity"
                  android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
                <category android:name="android.intent.category.HOME" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
    </application>
</manifest>
````

---

## 2. Android.bp

Place this in your app folder (e.g. `packages/apps/DiagnosticApp/Android.bp`)

---

## 3. Add to Product Build (`device.mk` or `product.mk`)

Ensure the app is included in the image:

```make
PRODUCT_PACKAGES += DiagnosticApp
```

---

## 4. Build and Install

```bash
# Build the app
m DiagnosticApp

# Verify app in system image
ls out/target/product/<device>/system/priv-app/DiagnosticApp/

# Reflash or push manually
adb remount
adb push out/target/product/<device>/system/priv-app/DiagnosticApp/DiagnosticApp.apk /system/priv-app/DiagnosticApp/
adb reboot
```

---

## 5. Verify App as Launcher

After boot:

```bash
adb shell pm list packages | grep diagnostic
```

Confirm default launcher resolution:

```bash
adb shell cmd package resolve-activity --brief \
  --components com.example.diagnostic_android_app/.MainActivity \
  android.intent.action.MAIN android.intent.category.HOME
```


 