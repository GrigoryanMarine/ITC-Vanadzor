public class OrdersByPlaces {

/**
@param productName name of product, which will be bought by username
@param count count of products, which in one place
@param username name of user, which should be bring order
*/

    String productName;
    int count;
    String username;

/**
@detailed This method gets productName, count, username and saves it
*/

    public OrdersByPlaces (String productName,int count,String username) {
      this.productName = productName;
      this.count = count;
      this.username = username;
    }
}

