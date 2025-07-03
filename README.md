# DiagnosticAppSoong

Here’s a clean, **technically professional** way to document this in your `README.md`:

---

### 🛠️ Integrating `DiagnosticApp` into the AOSP Build

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

