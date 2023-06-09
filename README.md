# Тестовое задание для deeplay

Данный сделан для тестового задания от компании deeplay

---

## Использование

Для использвания приложения потребуюутся 2 файла формата JSON.

В данном примере данные которые храняться в файлах будут соответсвовать данным из условия задачи.

***data.json*** - файл в который будет хранить в себе все сущности для дальнейшего сравнения по правилам.
Файл может быть также модифицирован и дополнен, используя другие названия полей и т.д.
<br>
<br>
Пример data.json:
```json
[
  {
    "weight": "Легкое",
    "height": "Маленькое",
    "type": "Всеядное"
  },
  {
    "weight": "Тяжелое",
    "height": "Маленькое",
    "type": "Травоядное"
  },
  {
    "weight": "Тяжелое",
    "height": "Невысокое",
    "type": "Травоядное"
  }
]
```
---

***rules.json*** - файл хранящий в себе "правила", по которым будет производиться подсчет всех сущностей взятых из
файла data.json. <br> Имеет строгую структуру, по которой записываются все правила в программу.
Имеет различные вариации при поиске:

+ "in" - используя для поиска элиментов которые входят в массив указанных значений
+ "notIn" - используя для поиска элиментов которые ***НЕ*** входят в массив значений
+ "equals" - используется поиска по одинаковому элименту 
+ "notEquals" - используется поиска элименту который не равен значению


Пример rules.json

```json
[
  {
    "name": "Число травоядных",
    "condition": [
      {
        "property": "type",
        "type": "equals",
        "value": "Травоядное"
      }
    ]
  },
  {
    "name": "Число травоядных или плотоядных и они при этом маленькие",
    "condition": [
      {
        "property": "type",
        "type": "in",
        "value": [
          "Травоядное",
          "Плотоядное"
        ]
      },
      {
        "property": "height",
        "type": "equals",
        "value": "Маленькое"
      }
    ]
  },
  {
    "name": "Число всеядных, но не являются высокими",
    "condition": [
      {
        "property": "type",
        "type": "equals",
        "value": "Всеядное"
      },
      {
        "property": "height",
        "type": "notEquals",
        "value": "Высокое"
      }
    ]
  }
]
```

---

### Работа при запуске программы

При запуске программы нужно вести комманды:

1. Комманда для загрузки данных:

 > load jsonFile src/main/resources/data.json


2. Комманда для вывода колличества объектов соответсвуйщие условиям:

 >  process count jsonFile src/main/resources/rules.json

Комманды вводятся последовательно.
