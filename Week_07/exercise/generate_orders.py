if __name__ == '__main__':
    with open('orders.txt', mode='w', encoding='utf8') as order_file:
        for index in range(1, 1000001):
            order_file.write("O{oid:05x},U{uid:05x},S{sku:05x},{total}\n".format(
                oid=index, uid=index, sku=index, total=index))
