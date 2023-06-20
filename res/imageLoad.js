conn = Mongo();
db = conn.getDB("image_store_db");

db.counters.insert(
    {
       _id: "imageId",
       seq: 1000000000
    }
 )

 function getNextSequenceImageId(name) {
    var ret = db.counters.findAndModify(
           {
             query: { _id: name },
             update: { $inc: { seq: 1 } },
             new: true
           }
    );
 
    return ret.seq;
 }

db.images.insertMany( [
    {
          "imageId": getNextSequenceImageId("imageId"),
          "title": "Image 1",
          "description": "Image 1 Description",
          "seller": 12345678,
          "likes": 100,
          "views": 500,
          "price": 9.99,
          "status": "sold",
          "imageLocation": "/path/to/image1.jpg",
          "tags":["tag01","tag02"]
    },
    {
          "imageId": getNextSequenceImageId("imageId"),
          "title": "Image 2",
          "description": "Image 2 Description",
          "seller": 87654321,
          "likes": 50,
          "views": 200,
          "price": 19.99,
          "status": "available",
          "imageLocation": "/path/to/image2.jpg",
          "tags":["tag03","tag04"]
    },
    {
          "imageId": getNextSequenceImageId("imageId"),
          "title": "Image 3",
          "description": "Image 3 Description",
          "seller": 12345678,
          "likes": 75,
          "views": 300,
          "price": 14.99,
          "status": "sold",
          "imageLocation": "/path/to/image3.jpg",
          "tags":["tag01","tag02"]
    },
    {
          "imageId": getNextSequenceImageId("imageId"),
          "title": "Image 4",
          "description": "Image 4 Description",
          "seller": 87654321,
          "likes": 120,
          "views": 800,
          "price": 24.99,
          "status": "onhold",
          "imageLocation": "/path/to/image4.jpg",
          "tags":["tag01","tag12"]
    },
    {
          "imageId": getNextSequenceImageId("imageId"),
          "title": "Image 5",
          "description": "Image 5 Description",
          "seller": 12345678,
          "likes": 50,
          "views": 150,
          "price": 9.99,
          "status": "available",
          "imageLocation": "/path/to/image5.jpg",
          "tags":["tag10","tag02"]
    },
    {
          "imageId": getNextSequenceImageId("imageId"),
          "title": "Image 6",
          "description": "Image 6 Description",
          "seller": 12345678,
          "likes": 90,
          "views": 400,
          "price": 12.99,
          "status": "sold",
          "imageLocation": "/path/to/image6.jpg",
          "tags":["tag04","tag02"]
    },
    {
          "imageId": getNextSequenceImageId("imageId"),
          "title": "Image 7",
          "description": "Image 7 Description",
          "seller": 87654321,
          "likes": 60,
          "views": 250,
          "price": 17.99,
          "status": "available",
          "imageLocation": "/path/to/image7.jpg",
          "tags":["tag01","tag04"]
    },
    {
          "imageId": getNextSequenceImageId("imageId"),
          "title": "Image 8",
          "description": "Image 8 Description",
          "seller": 12345678,
          "likes": 110,
          "views": 700,
          "price": 21.99,
          "status": "sold",
          "imageLocation": "/path/to/image8.jpg",
          "tags":["tag01","tag05"]
    },
    {
          "imageId": getNextSequenceImageId("imageId"),
          "title": "Image 9",
          "description": "Image 9 Description",
          "seller": 87654321,
          "likes": 80,
          "views": 300,
          "price": 15.99,
          "status": "available",
          "imageLocation": "/path/to/image9.jpg",
          "tags":["tag06","tag02"]
    },
    {
          "imageId": getNextSequenceImageId("imageId"),
          "title": "Image 10",
          "description": "Image 10 Description",
          "seller": 12345678,
          "likes": 70,
          "views": 200,
          "price": 11.99,
          "status": "available",
          "imageLocation": "/path/to/image10.jpg",
          "tags":["tag07","tag08"]
    }
])