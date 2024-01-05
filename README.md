# Versati

  Мобильное приложение со следующим функционалом:
* Авторизация и регистрация с использованием почты;
* Генерация QR-кода на основе введённых параметров:
  - Данные (URL или простой текст);
  - Размер изображения;
  - Цвета QR-кода и фона;
  - Формат изображения.
* Отправка QR-кода в мессенджеры или загрузка на устройство;
* Просмотр истории генерации QR-кодов.

## Скриншоты:
* **Авторизация:**
<image src='https://github.com/arshapshap/versati/assets/48681339/5ef80259-70fc-405c-a96b-35dbeddc55f8' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/a94402f1-882a-4377-87d7-7a3f76dfe3bb' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/7b703312-df6a-4740-9e1a-eec948a621cd' width=200 />
<br>
<br>

* **Генерация QR-кодов:**

<image src='https://github.com/arshapshap/versati/assets/48681339/c64d469e-67e0-4c84-b629-8949e27f55b0' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/a2905dff-3d66-4efb-8c36-7e841826c80d' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/9bd5e737-ae78-4e25-9029-cb6bd6cbf794' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/9449e3ea-b0a3-4b7d-8c0a-ec72a48edb49' width=200 />

## Архитектура
* Clean Architecture
* MVI
* Многомодульность

## Используемые технологии
* Kotlin
* Android SDK
* Compose
* Orbit-MVI
* Coroutines
* Koin
* Retrofit, Kotlinx-Serialization
* Room
* Navigation Compose
* Coil
* Firebase:
  - Authentication
  - Analytics
  - Crashlytics
  - Perfomance monitoring
  - Messaging

## Используемые API
* [GoQR](https://goqr.me/api/)
