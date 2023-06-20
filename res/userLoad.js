conn = Mongo();
db = conn.getDB("image_store_db");

db.counters.insertOne(
   {
      _id: "userid",
      seq: 10000
   }
)

function getNextSequenceUserId(name) {
    var ret = db.counters.findAndModify(
           {
             query: { _id: name },
             update: { $inc: { seq: 1 } },
             new: true
           }
    );

    return ret.seq;
}

db.users.insertMany( [
    {
        "userId": getNextSequenceUserId("userid"),
        "email": "user0@example.com",
        "firstName": "Alex",
        "lastName": "Ferit",
        "phone": 7538642910,
        "address": "65 Wser St, City",
        "password": "hashed_password",
        "role": ["user", "admin"],
        "status": "active",
        "cart": [],
        "likes": [],
        "transaction": []
    },
    {
        "userId": getNextSequenceUserId("userid"),
        "email": "user1@example.com",
        "firstName": "Jane",
        "lastName": "Smith",
        "phone": 9876543210,
        "address": "456 Elm St, City",
        "password": "hashed_password",
        "role": ["user"],
        "status": "active",
        "cart": [
            357896124583,
            123985214698,
            587436521007,
            500005214500
        ],
        "likes": [
            543210987654
        ],
        "transaction": [
            1234567890,
            2586470125,
            3528941001
        ]
    },
    {
        "userId": getNextSequenceUserId("userid"),
        "email": "user2@example.com",
        "firstName": "John",
        "lastName": "Doe",
        "phone": 1234567890,
        "address": "123 Main St, City",
        "password": "hashed_password",
        "role": ["user"],
        "status": "active",
        "cart": [
            123456789012,
            987654321098,
            456789012345,
            543210987654
        ],
        "likes": [
            500005214500,
            369584217015
        ],
        "transaction": [
            2468135790,
            9876543210
        ]
    },
    {
        "userId": getNextSequenceUserId("userid"),
        "email": "user3@example.com",
        "firstName": "Sarah",
        "lastName": "Johnson",
        "phone": 9876543210,
        "address": "789 Oak St, City",
        "password": "hashed_password",
        "role": ["user"],
        "status": "active",
        "cart": [
            246813579135,
            951357924680,
            789012345678
        ],
        "likes": [
            251496301254,
            789456123012,
            654987120331,
            230000120015
        ],
        "transaction": [
            4321098765,
            5678901234,
            1357924680
        ]
    },
    {
        "userId": getNextSequenceUserId("userid"),
        "email": "user4@example.com",
        "firstName": "Michael",
        "lastName": "Brown",
        "phone": 1234567890,
        "address": "789 Maple St, City",
        "password": "hashed_password",
        "role": ["user"],
        "status": "active",
        "cart": [
            789012345678
        ],
        "likes": [
            251496301254,           
            230000120015
        ],
        "transaction": [
            9012345678
        ]
    },
    {
        "userId": getNextSequenceUserId("userid"),
        "email": "user5@example.com",
        "firstName": "Emily",
        "lastName": "Wilson",
        "phone": 9876543210,
        "address": "456 Pine St, City",
        "password": "hashed_password",
        "role": ["user"],
        "status": "active",
        "cart": [
            876543210987,
            135792468013,
            246801357924,
            987654321089
        ],
        "likes": [
            326532653265,
            987474801201
        ],
        "transaction": [
            8765432109,
            3456789012,
            2109876543
        ]
    },
    {
        "userId": getNextSequenceUserId("userid"),
        "email": "user5@example.com",
        "firstName": "Alice",
        "lastName": "Nickman",
        "phone": 3526897411,
        "address": "9865 Friday St, City",
        "password": "hashed_password",
        "role": ["user"],
        "status": "inactive",
        "cart": [
            125654321089
        ],
        "likes": [ ],
        "transaction": [
            2109876001
        ]
    }
])