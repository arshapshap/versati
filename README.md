# Versati

  Мобильное приложение со следующим функционалом:
* Авторизация и регистрация с использованием почты;
* Генерация QR-кода на основе введённых параметров:
  - Данные (URL или простой текст);
  - Размер изображения;
  - Цвета QR-кода и фона;
  - Формат изображения.
* Отправка QR-кода в мессенджеры или загрузка на устройство;
* Просмотр истории генерации QR-кодов;
* Парсинг текста с изображения по ссылке.

В разработке:
* [ ] Парсинг текста с изображения на устройстве;
* [ ] Просмотр истории парсинга текста.

## Скриншоты:
* **Авторизация:**
<image src='https://github.com/arshapshap/versati/assets/48681339/5ef80259-70fc-405c-a96b-35dbeddc55f8' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/a94402f1-882a-4377-87d7-7a3f76dfe3bb' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/7b703312-df6a-4740-9e1a-eec948a621cd' width=200 />
<br>
<br>

* **Генерация QR-кодов:**

<image src='https://github.com/arshapshap/versati/assets/48681339/d8fd7512-c40c-4094-9d69-5436da73cfb8' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/298e335d-6840-4d25-9dcf-56de8dd89eec' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/c3abb274-592a-4af3-adcd-97b3ad6b0e38' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/459f374a-7d72-495b-8348-6b9defb1eeaa' width=200 />
<br>
<br>

* **Парсинг текста:**

<image src='https://github.com/arshapshap/versati/assets/48681339/48e0ac3e-c277-4696-a823-78d537bf2064' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/78de3aee-8502-4c4f-b1e7-5b029edbe079' width=200 />

Для примера на скриншоте использовалось [это изображение](https://favim.com/pd/s6/orig/61/text-harry-potter-hermione-Favim.com-576725.jpg)

## Скачивание
      
  * APK-файл доступен для скачивания в разделе Releases:

    - [v0.2](https://github.com/arshapshap/versati/releases/tag/v1)

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
  - Remote Config

## Используемые API
* [GoQR](https://goqr.me/api/)
* [OCR API](https://ocr.space/OCRAPI)
