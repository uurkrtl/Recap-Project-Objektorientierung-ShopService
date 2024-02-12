
# Shop Service (Advanced)

## [](https://github.com/neuefische/hh-java-24-1-handouts/blob/main/3-Ecosystem/08-Recap-Project/challenges.md#coding-shopservice)Coding: ShopService

Today, everything revolves around the ShopService task. Add or write tests for all steps in the task, unless otherwise specified.

Clone the sample solution:  [ShopService](https://github.com/Flooooooooooorian/Recap-Project-Objektorientierung-ShopService). You will be working in new group configurations today and should gain experience working with unfamiliar code. Therefore, please work based on the sample solution from the last module.

Now remove the link to Florian's GitHub repository by selecting "Git" in the menu, then "Manage Remotes," and removing the entry "origin."

Upload the project as a new repository on GitHub to one of your own GitHub accounts: In IntelliJ, select "Git" in the cloned project, then "GitHub," and choose "Share Project on GitHub."

The sample solution is now the main branch of your GitHub repository.

What is the link to your GitHub repository?  `inputfield`

## [](https://github.com/neuefische/hh-java-24-1-handouts/blob/main/3-Ecosystem/08-Recap-Project/challenges.md#coding-order-status)Coding: Order Status

Add an order status to the Order (PROCESSING, IN_DELIVERY, COMPLETED) to determine the status of the order.

To do this, create a new branch, create and push the commits, create a pull request, review the PR, and merge it into the main branch.

What is the link to your pull request?  `inputfield`

## [](https://github.com/neuefische/hh-java-24-1-handouts/blob/main/3-Ecosystem/08-Recap-Project/challenges.md#coding-order-status-1)Coding: Order Status

Write a method in the ShopService that returns a list of all orders with a specific order status (parameter) using streams.

## [](https://github.com/neuefische/hh-java-24-1-handouts/blob/main/3-Ecosystem/08-Recap-Project/challenges.md#coding-optional-product)Coding: Optional Product

Modify the 'getProductById' method in your ProductRepo so that it returns an Optional if the product exists, otherwise an empty Optional.

## [](https://github.com/neuefische/hh-java-24-1-handouts/blob/main/3-Ecosystem/08-Recap-Project/challenges.md#coding-exceptions)Coding: Exceptions

Modify the 'addOrder' method in the ShopService so that an exception is thrown if the product does not exist.

## [](https://github.com/neuefische/hh-java-24-1-handouts/blob/main/3-Ecosystem/08-Recap-Project/challenges.md#coding-lombok)Coding: Lombok

Add an 'updateOrder' method in the ShopService that updates the Order based on an orderId and a new order status. Use the Lombok  `@With`  annotation for this.

## [](https://github.com/neuefische/hh-java-24-1-handouts/blob/main/3-Ecosystem/08-Recap-Project/challenges.md#coding-order-date)Coding: Order Date

Extend the Order object with a field that stores the order timestamp. In the 'addOrder' method, fill this field with the current timestamp.

This timestamp should be able to be used as evidence in court if customers claim that they did not place the order. Consider which data type is best suited for this, even if customers order from abroad.