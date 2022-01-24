#!/bin/bash
while read -r line; do
    echo $line | rabbitmqadmin -u admin -p admin publish exchange=event.type routing_key=events ;
done < events_list.txt