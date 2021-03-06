/**
 * @package com.itcvanadzor.lunch.database
 */

package com.itcvanadzor.lunch.database;

/**
@detailed class working with database Products table
*/

public class Products {

/**
*@param productName product name of current order
*@param productId unique value for product name 
*@param count count of products 
*/

    String productName;
    int  productId;
    int count;

/**
@detailed This method gets productName and productId and saves it
*/
    public Products (String productName, int productId) {
        this.productName=productName;
        this.productId=productId;
    }

/**
@detailed This method gets productName, productId and count and saves it
*/

    public Products(String productName, int productId, int count) {
        this.productName = productName;
        this.productId = productId;
        this.count = count;
    }

    /**
     *@detailed This method get product name
     *@return productName
     */

    public String getProductName() {
        return productName;
    }

    /**
     *@detailed This method get product id
     *@return productId
     */

    public int getProductId() {
        return productId;
    }

    /**
     *@detailed This method get product count 
     *@return count
     */

    public int getCount() {
        return count;
    }
}
