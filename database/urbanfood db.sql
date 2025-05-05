/*Tables*/

CREATE TABLE "URBANFOOD"."ADMIN" 
   (	"ADMIN_ID" NUMBER, 
	"ADMIN_USERNAME" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"ADMIN_MAIL" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"ADMIN_PASSWORD" VARCHAR2(100 BYTE) NOT NULL ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
ALTER TABLE "URBANFOOD"."ADMIN" ADD PRIMARY KEY ("ADMIN_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;


CREATE TABLE "URBANFOOD"."CART" 
   (	"CART_ID" NUMBER, 
	"USER_ID" NUMBER, 
	"PRODUCT_ID" NUMBER, 
	"QUANTITY" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
ALTER TABLE "URBANFOOD"."CART" ADD PRIMARY KEY ("CART_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
ALTER TABLE "URBANFOOD"."CART" ADD FOREIGN KEY ("USER_ID")
	  REFERENCES "URBANFOOD"."USERS" ("USER_ID") ENABLE;
ALTER TABLE "URBANFOOD"."CART" ADD FOREIGN KEY ("PRODUCT_ID")
	  REFERENCES "URBANFOOD"."PRODUCTS" ("PRODUCT_ID") ENABLE;


CREATE TABLE "URBANFOOD"."ORDERS" 
   (	"ORDER_ID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	"USER_ID" NUMBER NOT NULL ENABLE, 
	"TOTAL_AMOUNT" NUMBER(10,2) NOT NULL ENABLE, 
	"ORDER_DATE" DATE DEFAULT SYSDATE, 
	"DELIVERY_ADDRESS" VARCHAR2(500 BYTE) NOT NULL ENABLE, 
	"PAYMENT_METHOD" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"STATUS" VARCHAR2(50 BYTE) DEFAULT 'Pending'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
ALTER TABLE "URBANFOOD"."ORDERS" ADD PRIMARY KEY ("ORDER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
ALTER TABLE "URBANFOOD"."ORDERS" ADD FOREIGN KEY ("USER_ID")
	  REFERENCES "URBANFOOD"."USERS" ("USER_ID") ENABLE;



CREATE TABLE "URBANFOOD"."ORDER_ITEMS" 
   (	"ORDER_ITEM_ID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	"ORDER_ID" NUMBER NOT NULL ENABLE, 
	"PRODUCT_ID" NUMBER NOT NULL ENABLE, 
	"QUANTITY" NUMBER NOT NULL ENABLE, 
	"PRICE_AT_PURCHASE" NUMBER(10,2) NOT NULL ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
ALTER TABLE "URBANFOOD"."ORDER_ITEMS" ADD PRIMARY KEY ("ORDER_ITEM_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
ALTER TABLE "URBANFOOD"."ORDER_ITEMS" ADD FOREIGN KEY ("ORDER_ID")
	  REFERENCES "URBANFOOD"."ORDERS" ("ORDER_ID") ENABLE;
ALTER TABLE "URBANFOOD"."ORDER_ITEMS" ADD FOREIGN KEY ("PRODUCT_ID")
	  REFERENCES "URBANFOOD"."PRODUCTS" ("PRODUCT_ID") ENABLE;


CREATE TABLE "URBANFOOD"."PRODUCTS" 
   (	"PRODUCT_ID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	"NAME" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"DESCRIPTION" VARCHAR2(1000 BYTE), 
	"PRICE" NUMBER(10,2) NOT NULL ENABLE, 
	"OLD_PRICE" NUMBER(10,2), 
	"STOCK" NUMBER DEFAULT 100, 
	"IMAGE_MAIN" VARCHAR2(255 BYTE), 
	"IMAGE_GALLERY" CLOB, 
	"CATEGORY" VARCHAR2(100 BYTE), 
	"BADGE" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 
 LOB ("IMAGE_GALLERY") STORE AS SECUREFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW 4000 CHUNK 8192
  NOCACHE LOGGING  NOCOMPRESS  KEEP_DUPLICATES 
  STORAGE(INITIAL 262144 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
ALTER TABLE "URBANFOOD"."PRODUCTS" ADD PRIMARY KEY ("PRODUCT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;


CREATE TABLE "URBANFOOD"."USERS" 
   (	"USER_ID" NUMBER, 
	"USERNAME" VARCHAR2(50 BYTE), 
	"PASSWORD" VARCHAR2(100 BYTE), 
	"EMAIL" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
ALTER TABLE "URBANFOOD"."USERS" ADD PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
ALTER TABLE "URBANFOOD"."USERS" ADD UNIQUE ("USERNAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;



/*Procedures*/
create or replace PROCEDURE AddToCart(
    p_user_id     IN NUMBER,
    p_product_id  IN NUMBER,
    p_quantity    IN NUMBER DEFAULT 1
) AS
BEGIN
    DECLARE
        v_quantity NUMBER := NVL(p_quantity, 1); -- Default to 1 if NULL
        v_count    NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM URBANFOOD.Cart
        WHERE user_id = p_user_id AND product_id = p_product_id;

        IF v_count > 0 THEN
            -- Update existing cart item
            UPDATE URBANFOOD.Cart
            SET quantity = quantity + v_quantity
            WHERE user_id = p_user_id AND product_id = p_product_id;
        ELSE
            -- Insert new cart item
            INSERT INTO URBANFOOD.Cart (cart_id, user_id, product_id, quantity)
            VALUES (
                URBANFOOD.Cart_seq.NEXTVAL,
                p_user_id,
                p_product_id,
                v_quantity
            );
        END IF;
    END;
END;






create or replace PROCEDURE ADMIN_LOGIN_PROC (
    p_username IN VARCHAR2,
    p_password IN VARCHAR2,
    p_result OUT NUMBER
) AS
BEGIN
    SELECT COUNT(*)
    INTO p_result
    FROM ADMIN
    WHERE LOWER(ADMIN_USERNAME) = LOWER(p_username)
      AND ADMIN_PASSWORD = p_password;
END;




create or replace PROCEDURE CALCULATE_CART_TOTAL (
    p_user_id       IN  NUMBER,
    p_total         OUT NUMBER,
    p_vat           OUT NUMBER,
    p_shipping      OUT NUMBER
) AS
    v_subtotal      NUMBER := 0;
    v_vat           NUMBER := 0;
    v_shipping      NUMBER := 250;  
    v_cat_vat       NUMBER := 0;

    CURSOR cart_cursor IS
        SELECT p.price, c.quantity, p.category
        FROM URBANFOOD.Cart c
        JOIN URBANFOOD.Products p ON c.product_id = p.product_id
        WHERE c.user_id = p_user_id;
BEGIN
    FOR rec IN cart_cursor LOOP

        CASE rec.category
            WHEN 'Food & Drinks' THEN v_cat_vat := 0.05;
            WHEN 'Vegetables' THEN v_cat_vat := 0.02;
            WHEN 'Dried Foods' THEN v_cat_vat := 0.03;
            WHEN 'Bread & Cake' THEN v_cat_vat := 0.04;
            WHEN 'Fish & Meat' THEN v_cat_vat := 0.06;
            ELSE v_cat_vat := 0.05;
        END CASE;

        -- Calculate subtotal and VAT
        v_subtotal := v_subtotal + (rec.price * rec.quantity);
        v_vat := v_vat + (rec.price * rec.quantity * v_cat_vat);
    END LOOP;

    -- Set OUT parameters
    p_total := v_subtotal;
    p_vat := v_vat;
    p_shipping := v_shipping;
END;




create or replace PROCEDURE DELETE_PRODUCT_BY_ID (
    p_product_id IN NUMBER
)
AS
BEGIN
    DELETE FROM URBANFOOD.PRODUCTS
    WHERE PRODUCT_ID = p_product_id;

    COMMIT;
END;





create or replace PROCEDURE GetUserCartItems (
    p_user_id IN NUMBER,
    p_cart_items OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_cart_items FOR
    SELECT c.CART_ID, c.PRODUCT_ID, c.QUANTITY,
           p.NAME AS product_name, p.PRICE,
           p.IMAGE_MAIN AS image_url 
    FROM URBANFOOD.Cart c
    JOIN URBANFOOD.Products p ON c.PRODUCT_ID = p.PRODUCT_ID
    WHERE c.USER_ID = p_user_id;
END;




create or replace PROCEDURE           GET_ALL_CATEGORIES(
    p_categories OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_categories FOR
    SELECT DISTINCT 
        category
    FROM 
        URBANFOOD.PRODUCTS
    ORDER BY 
        category;
END GET_ALL_CATEGORIES;





create or replace PROCEDURE GET_ALL_ORDERS (
    p_orders OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_orders FOR
    SELECT ORDER_ID, USER_ID, TOTAL_AMOUNT, ORDER_DATE, DELIVERY_ADDRESS, PAYMENT_METHOD, STATUS
    FROM URBANFOOD.ORDERS
    ORDER BY ORDER_DATE DESC;
END;




create or replace PROCEDURE           GET_ALL_PRODUCTS(
    p_products OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_products FOR
    SELECT 
        product_id, 
        name, 
        description, 
        price, 
        old_price, 
        stock, 
        image_main, 
        image_gallery, 
        category, 
        badge
    FROM 
        URBANFOOD.PRODUCTS
    ORDER BY 
        category, product_id;
END GET_ALL_PRODUCTS;




create or replace PROCEDURE GET_CART_ITEM_COUNT(p_user_id IN NUMBER, p_item_count OUT NUMBER) AS
BEGIN

    SELECT SUM(QUANTITY)
    INTO p_item_count
    FROM URBANFOOD.CART
    WHERE USER_ID = p_user_id;

    IF p_item_count IS NULL THEN
        p_item_count := 0;
    END IF;
END;




create or replace PROCEDURE GET_CART_ITEM_COUNT(p_user_id IN NUMBER, p_item_count OUT NUMBER) AS
BEGIN

    SELECT SUM(QUANTITY)
    INTO p_item_count
    FROM URBANFOOD.CART
    WHERE USER_ID = p_user_id;

    IF p_item_count IS NULL THEN
        p_item_count := 0;
    END IF;
END;




create or replace PROCEDURE           GET_PRODUCTS_BY_CATEGORY(
    p_category IN VARCHAR2,
    p_products OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_products FOR
    SELECT 
        product_id, 
        name, 
        description, 
        price, 
        old_price, 
        stock, 
        image_main, 
        image_gallery, 
        category, 
        badge
    FROM 
        URBANFOOD.PRODUCTS 
    WHERE 
        category = p_category
    ORDER BY 
        product_id;
END GET_PRODUCTS_BY_CATEGORY;





create or replace PROCEDURE GET_PRODUCT_BY_ID(
    p_product_id IN NUMBER,
    p_name OUT VARCHAR2,
    p_description OUT VARCHAR2,
    p_price OUT NUMBER,
    p_old_price OUT NUMBER,
    p_stock OUT NUMBER,
    p_image_main OUT VARCHAR2,
    p_image_gallery OUT CLOB,
    p_category OUT VARCHAR2,
    p_badge OUT VARCHAR2
)
IS
BEGIN
    SELECT NAME, DESCRIPTION, PRICE, OLD_PRICE, STOCK, IMAGE_MAIN, IMAGE_GALLERY, CATEGORY, BADGE
    INTO p_name, p_description, p_price, p_old_price, p_stock, p_image_main, p_image_gallery, p_category, p_badge
    FROM URBANFOOD.PRODUCTS
    WHERE PRODUCT_ID = p_product_id;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_name := NULL;
        p_description := NULL;
        p_price := NULL;
        p_old_price := NULL;
        p_stock := NULL;
        p_image_main := NULL;
        p_image_gallery := NULL;
        p_category := NULL;
        p_badge := NULL;
    WHEN OTHERS THEN
        RAISE;
END GET_PRODUCT_BY_ID;





create or replace PROCEDURE INSERT_PRODUCT(
    p_name           IN VARCHAR2,
    p_description    IN VARCHAR2,
    p_price          IN NUMBER,
    p_old_price      IN NUMBER,
    p_stock          IN NUMBER,
    p_image_main     IN VARCHAR2,
    p_image_gallery  IN CLOB,
    p_category       IN VARCHAR2,
    p_badge          IN VARCHAR2
) AS
BEGIN
    INSERT INTO URBANFOOD.PRODUCTS (
        NAME,
        DESCRIPTION,
        PRICE,
        OLD_PRICE,
        STOCK,
        IMAGE_MAIN,
        IMAGE_GALLERY,
        CATEGORY,
        BADGE
    ) VALUES (
        p_name,
        p_description,
        p_price,
        p_old_price,
        NVL(p_stock, 100),
        p_image_main,
        p_image_gallery,
        p_category,
        p_badge
    );

    COMMIT;
END;





create or replace PROCEDURE           place_order (
    p_user_id IN NUMBER,
    p_total_amount IN NUMBER,
    p_delivery_address IN VARCHAR2,
    p_payment_method IN VARCHAR2
) AS
    v_order_id NUMBER;
BEGIN

    INSERT INTO URBANFOOD.Orders (
        user_id,
        total_amount,
        delivery_address,
        payment_method
    ) VALUES (
        p_user_id,
        p_total_amount,
        p_delivery_address,
        p_payment_method
    ) RETURNING order_id INTO v_order_id;

    -- 2. Insert into Order_Items
    FOR cart_item IN (
        SELECT c.product_id, c.quantity, p.price
        FROM URBANFOOD.Cart c
        JOIN URBANFOOD.Products p ON c.product_id = p.product_id
        WHERE c.user_id = p_user_id
    ) LOOP
        INSERT INTO URBANFOOD.Order_Items (
            order_id,
            product_id,
            quantity,
            price_at_purchase
        ) VALUES (
            v_order_id,
            cart_item.product_id,
            cart_item.quantity,
            cart_item.price
        );
    END LOOP;

    -- 3. Clear the user's cart
    DELETE FROM URBANFOOD.Cart WHERE user_id = p_user_id;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END;




create or replace PROCEDURE REMOVE_CART_ITEM (
    p_cart_id IN NUMBER
)
IS
BEGIN
    DELETE FROM URBANFOOD.CART
    WHERE CART_ID = p_cart_id;

    COMMIT;
END;





create or replace PROCEDURE UpdateCartItem (
    p_cart_id IN NUMBER,
    p_user_id IN NUMBER,
    p_product_id IN NUMBER,
    p_quantity IN NUMBER,
    p_status OUT VARCHAR2
) AS
    v_count NUMBER;
BEGIN
    -- Check if the cart item exists and belongs to the user
    SELECT COUNT(*) INTO v_count
    FROM URBANFOOD.Cart
    WHERE cart_id = p_cart_id 
    AND user_id = p_user_id;

    IF v_count = 0 THEN
        p_status := 'ERROR: Cart item not found or does not belong to this user';
        RETURN;
    END IF;

    -- If quantity is zero or negative, remove the item from cart
    IF p_quantity <= 0 THEN
        DELETE FROM URBANFOOD.Cart
        WHERE cart_id = p_cart_id;

        p_status := 'SUCCESS: Item removed from cart';
    ELSE
        -- Update the quantity
        UPDATE URBANFOOD.Cart
        SET quantity = p_quantity,
            product_id = p_product_id
        WHERE cart_id = p_cart_id;

        p_status := 'SUCCESS: Cart updated';
    END IF;

    -- Commit the transaction
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        p_status := 'ERROR: ' || SQLERRM;
END;




create or replace PROCEDURE UPDATE_PRODUCT(
    p_product_id     IN URBANFOOD.PRODUCTS.PRODUCT_ID%TYPE,
    p_name           IN URBANFOOD.PRODUCTS.NAME%TYPE,
    p_description    IN URBANFOOD.PRODUCTS.DESCRIPTION%TYPE,
    p_price          IN URBANFOOD.PRODUCTS.PRICE%TYPE,
    p_old_price      IN URBANFOOD.PRODUCTS.OLD_PRICE%TYPE,
    p_stock          IN URBANFOOD.PRODUCTS.STOCK%TYPE,
    p_category       IN URBANFOOD.PRODUCTS.CATEGORY%TYPE,
    p_badge          IN URBANFOOD.PRODUCTS.BADGE%TYPE
) AS
BEGIN
    UPDATE URBANFOOD.PRODUCTS
    SET 
        NAME = p_name,
        DESCRIPTION = p_description,
        PRICE = p_price,
        OLD_PRICE = p_old_price,
        STOCK = p_stock,
        CATEGORY = p_category,
        BADGE = p_badge
    WHERE PRODUCT_ID = p_product_id;
END;


/*Sequances*/
CREATE SEQUENCE admin_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


CREATE SEQUENCE cart_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


CREATE SEQUENCE user_id_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;