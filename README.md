# operator-merge-adder

Takes two values and adds them together. Sets the timestamp to the latest of both values.

Use this operator when adding values of different device types. Use [operator-adder](https://github.com/SENERGY-Platform/analytics-operator-adder) when adding values of the same device type.

Use the flow designer to first add all values of the same device types and this operator to add those added values.

## Inputs

* value1 (float): Reading from device 1
* value2 (float): Reading from device 2
* timestamp1 (string): Timestamp from device 1
* timestamp2 (string): Timestamp from device 2 

## Outputs

* value (float): Sum of value1 and value 2
* timestamp (string): Latest timestamp of either timestamp1 or timestamp2

## Configs

