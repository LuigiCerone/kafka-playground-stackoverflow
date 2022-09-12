from confluent_kafka import Producer


p = Producer({'bootstrap.servers': 'localhost:9092'})

for data in ['hello', 'world']:
    p.poll(0)
    
    p.produce('python-topic', data.encode('utf-8'))

p.flush()
