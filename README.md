Product-Service application:

A basic model of an e-commerce application.
Developed this application with MongoDb, Spring Boot technologies.

you need following tools for running this successfully:
- java 8
- local mongodo instance

please follow these steps:

Firstly, run the following queries in local mongodb instance

- name the local mongo db as Mart with default port 27017 (ref: application.yml for database instance props)
db.createCollection("products")

db.products.ensureIndex({name: "text"})

db.createCollection("sequences")

db.sequences.insertMany([{
	_id: "products",
	seq: 0
},
{
	_id: "categories",
	seq: 0
}
])

After successfully running the queries in mongodb,
clone this project and run the spring boot application:

Once the project is up & running, use the postman collections for sample data & end-points
or you can free to use Swagger Ui documentation to test the application.

Note: I have restricted the enhancements in many ways, as time is constraint.
But this model suits for all the product types in ecommerce applications.

I have mongodb full search for searching products -
we also other best proven methods like using lucene, solr and
elasticsearch.

