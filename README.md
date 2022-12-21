```main:```
![badge](https://github.com/Prodocc/Software_Engineering/actions/workflows/gradle-test.yml/badge.svg?branch=main)
```developer:```
![badge](https://github.com/Prodocc/Software_Engineering/actions/workflows/gradle-test.yml/badge.svg?branch=developer)
# Конвертер
____
Небольшой сервис, для конвертирования криптовалютЫ.
Позволяет конвертировать монеты Monero(XMR) в монеты Ripple(XRP).

Как пользоваться:
-----------
{value} - заменяем на число конвертируемой валюты,
{convertFromTo} - заменяем на:
`XRPtoXMR` - конвертировать из Ripple в Monero.   
`XMRtoXRP` — конвертировать из Monero в Ripple.   

```
http://localhost:9001/converter/convert/{convertFromTo}/{value}
```
Просмотр 10 последних конвертаций
```
http://localhost:9001/converter/convert/recentlyConverted
```
При помощи докера:
-----------

```
docker build -t converter .
docker run -it --rm -p 9001:9001 converter   
```