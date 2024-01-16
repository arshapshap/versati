# Versati

  Мобильное приложение со следующим функционалом:
* Авторизация и регистрация с использованием почты;
* Генерация QR-кода на основе введённых параметров:
  - Данные (URL или простой текст);
  - Размер изображения;
  - Цвет QR-кода и цвет фона;
  - Формат изображения.
* Отправка QR-кода в мессенджеры или загрузка на устройство;
* Просмотр истории генерации QR-кодов;
* Парсинг текста с изображения по ссылке;
* Просмотр истории парсинга текста.

## Скриншоты:
* **Авторизация и регистрация:**
<image src='https://github.com/arshapshap/versati/assets/48681339/5ef80259-70fc-405c-a96b-35dbeddc55f8' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/a94402f1-882a-4377-87d7-7a3f76dfe3bb' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/7b703312-df6a-4740-9e1a-eec948a621cd' width=200 />
<br>
<br>

* **Генерация QR-кодов и просмотр истории:**

<image src='https://github.com/arshapshap/versati/assets/48681339/d8fd7512-c40c-4094-9d69-5436da73cfb8' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/298e335d-6840-4d25-9dcf-56de8dd89eec' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/c3abb274-592a-4af3-adcd-97b3ad6b0e38' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/459f374a-7d72-495b-8348-6b9defb1eeaa' width=200 />
<br>
<br>

* **Парсинг текста и просмотр истории:**

<image src='https://github.com/arshapshap/versati/assets/48681339/a6726ae5-10be-4413-add9-09fcb3572416' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/3049303f-9ad9-4c7d-ba45-621c4cb44270' width=200 />
<image src='https://github.com/arshapshap/versati/assets/48681339/c426975d-fe38-4264-8071-5b3c82ca877f' width=200 />

Для примера на скриншоте использовалось [это изображение](https://favim.com/pd/s6/orig/61/text-harry-potter-hermione-Favim.com-576725.jpg).

## Скринкасты:
<details><summary>Авторизация и регистрация</summary>
<br>
  <image src='https://github.com/arshapshap/versati/assets/48681339/5534a2bf-907a-498f-a208-2b34086e412e' width=200 />
</details>
<details><summary>Генерация QR-кода</summary>
<br>
  <image src='https://github.com/arshapshap/versati/assets/48681339/6a4e888a-2ad2-4840-97e8-8fb53041e6b7' width=200 />
</details>
<details><summary>Просмотр истории генерации QR-кодов</summary>
<br>
  <image src='https://github.com/arshapshap/versati/assets/48681339/5a0f49aa-faed-4216-82f9-18c64620f788' width=200 />
</details>
<details><summary>Парсинг текста</summary>
<br>
  <image src='https://github.com/arshapshap/versati/assets/48681339/9f95ed99-7182-4cd0-ae36-c59c77d4b73e' width=200 />
    
Для примера на скринкасте использовался [этот файл](https://api.slingacademy.com/v1/sample-data/files/text-and-images.pdf).
</details>


## Скачивание
      
  * APK-файл доступен для скачивания в разделе Releases:

    - [Version 1](https://github.com/arshapshap/versati/releases/tag/v1)

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
