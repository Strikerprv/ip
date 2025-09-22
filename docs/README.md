# Dukey User Guide

![UI of Dukey Chatbot](./Ui.png)

Dukey Chatbot is a simple yet powerful task manager that helps you stay on top of your daily commitments. 
With its playful personality and easy-to-use commands, you can organize tasks in seconds.

## 📝 Adding Tasks
Dukey allows you to add 3 types of tasks: `Todo`, `Deadline` and `Event` tasks.
### Todo
The command takes in a task description and adds it into the task list.

Example: `todo Buy Groceries` or `t Buy Groceries`

Command can use `todo` or `t` (for ease of use) and will succesfully
add a todo task to the task list.

```
______________________________________
Pika Pika! Added a new task, pika:
[T] [ ] Buy Groceries
You now have 5 tasks in total, pika! ⚡
______________________________________
```

### Deadline
The command takes in a task description and a date (or time), and adds it into the task list.
Throws error if a date or time is not input as part of the command using the `/by` keyword.

Date or time input can be in the YYYY-MM-DD format or just a regular string.

Example: `deadline Buy Groceries /by 2025-10-01` or `d Buy Groceries /by 2025-10-01`

Command can use `deadline` or `d` (for ease of use) and will succesfully
add a deadline task to the task list.

```
______________________________________
Pika Pika! Added a new task, pika:
[D] [ ] Buy Groceries (by: Oct 1 2025)
You now have 6 tasks in total, pika! ⚡
______________________________________
```

Example: `deadline CS2100 Quiz /by 10 August`

```
______________________________________
Pika Pika! Added a new task, pika:
[D] [ ] CS2100 Quiz (by: 10 August)
You now have 6 tasks in total, pika! ⚡
______________________________________
```
Example: `deadline CS2103 ip`

Without the `/by` flag, this input is invalid and will raise an error.
```
______________________________________
Pika… WARNING: Deadline command requires a deadline.
Valid input requires '/by' keyword. Follow the format: deadline <task name> /by <date / timing>
Dates can be written in yyyy-mm-dd format or just plain text
E.g. deadline Wash Clothes /by 2025-08-12
______________________________________

```

### Event

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## 📋 List

## ✅ Mark and Unmark
* Mark
* Unmark
// Feature details

## ❌ Delete

## 🔍 Find


// Feature details