conn = Mongo();
db = conn.getDB("image_store_db");

db.counters.insert(
    {
       _id: "transactionId",
       seq: 1000000000
    }
 )

 function getNextSequenceTransactionId(name) {
    var ret = db.counters.findAndModify(
           {
             query: { _id: name },
             update: { $inc: { seq: 1 } },
             new: true
           }
    );
 
    return ret.seq;
 }

db.transactions.insertMany( [
    {
        "transactionId": getNextSequenceTransactionId("transactionId"),
        "date": "Thu May 24 2023 18:12:41 GMT-0600 (Mountain Daylight Saving Time)",
        "price": 35.98,
        "purchasedImages": [
            300215084920,
            301025011012
        ],
        "buyerEmail": "buyer1@example.com",
        "status": "Completed"
    },
    {
        "transactionId": getNextSequenceTransactionId("transactionId"),
        "date": "Fri May 25 2023 09:30:15 GMT-0600 (Mountain Daylight Saving Time)",
        "price": 42.50,
        "purchasedImages": [
            357896124583,
            123985214698,
            587436521007,
            500005214500
        ],
        "buyerEmail": "buyer2@example.com",
        "status": "Uncompleted"
    },
    {
        "transactionId": getNextSequenceTransactionId("transactionId"),
        "date": "Sat May 26 2023 15:45:00 GMT-0600 (Mountain Daylight Saving Time)",
        "price": 69.97,
        "purchasedImages": [
            741258963102,
            369852147025,
            258963147036
        ],
        "buyerEmail": "buyer3@example.com",
        "status": "Completed"
    },
    {
        "transactionId": getNextSequenceTransactionId("transactionId"),
        "date": "Sun May 27 2023 11:20:30 GMT-0600 (Mountain Daylight Saving Time)",
        "price": 25.99,
        "purchasedImages": [
            989875574879,
            823500814720
        ],
        "buyerEmail": "buyer4@example.com",
        "status": "Completed"
    },
    {
        "transactionId": getNextSequenceTransactionId("transactionId"),
        "date": "Mon May 28 2023 16:55:10 GMT-0600 (Mountain Daylight Saving Time)",
        "price": 8.75,
        "purchasedImages": [
            100052308001,
            235689741205,
            356824356800
        ],
        "buyerEmail": "buyer5@example.com",
        "status": "Completed"
    },
    {
        "transactionId": getNextSequenceTransactionId("transactionId"),
        "date": "Thu May 24 2023 18:12:41 GMT-0600 (Mountain Daylight Saving Time)",
        "price": 35.98,
        "purchasedImages": [
            200520032500,
            659800125801
        ],
        "buyerEmail": "buyer6@example.com",
        "status": "Unompleted"
    }
])