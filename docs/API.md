## API
  Get the latest rate persisted in the database.
* **URL**
  /exchangerate/v1/rates/latest
* **Method:**
  `GET`
* **Success Response:**
  * **Code:** 200 <br />
  **Content:** 
    ```json
    {
        "created": "2018-06-26",
        "rate": 1.1665
    }

* **Error Response:**
  * **Code:** 404 NOT_FOUND <br />
   
Get the historical rates from startDate to endDate as date format ruled by ISO 8601.
* **URL**
  /exchangerate/v1/rates/history?start_date=yyyy-MM-dd&end_date=yyyy-MM-dd
* **Method:**
  `GET`
* **Data RequestParams**
  **Required:**
   `start_date=yyyy-MM-dd`
   `end_date=yyyy-MM-dd`
* **Success Response:**
  * **Code:** 200 <br />
  **Content:** 
    ```json
    [
      {
          "created": "2018-06-24",
          "rate": 1.6753
      },
      {
          "created": "2018-06-25",
          "rate": 1.6753
      }
    ]
* **Error Response:**
  * **Code:** 404 NOT_FOUND <br />
    
