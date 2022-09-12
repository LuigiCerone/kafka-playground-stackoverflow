from confluent_kafka import Consumer

c = Consumer({
    'bootstrap.servers': 'localhost:9092',
    'group.id': 'python_group',
    'auto.offset.reset': 'earliest'
})

c.subscribe(['python-topic'])

while True:
    msg = c.poll(1.0)

    if msg is None:
        continue
    if msg.error():
        print(f"Error: {msg.error()}")
        continue

    print(f"Message: {msg.value().decode('utf-8')}")

c.close()
