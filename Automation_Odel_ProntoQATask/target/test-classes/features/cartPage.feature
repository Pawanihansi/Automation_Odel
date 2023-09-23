Feature: Test the all items are added into the cart.
  As a user
  Background:
    When I have opened the system


  Scenario Outline: Test the Cart Icon number after the adding items
    When I click on "Women_Containts"
    And I click on "Type_Casual"
    And I wait few seconds
    And I click on "Dress"
    And I click on "Brand_Button"
    And I wait few seconds
    And I click on "Brand_Odel"
    #And I select value without select class on "Brand_filter" by "<BrandName>"
    #And I click on "Brand_filter" starts with "<BrandName>" as text
    #And I select value without select class on "container" by "<dress_1>"
    #And I select cloth value without select class on "container" by "<dress_1>"
    And I wait few seconds
    And I click on "Odel_Dress1"
    And I click on "SelectSize"
    And I wait few seconds
    And I click on "Odel_Dress1_XL"
    And I wait few seconds
    And I get product selling price1 on "Odel_Dress1_Price"
    #And I select value without select class on "SizeDropdown" by "<size>"
    #And I select span class on "SizeDropdown"  by "<size>"
    And I click on "add_to_cart"
    #And wait page loading
    And I wait few seconds
    And I click on "Cart_Cut_Icon"
    And I wait few seconds
    And Get dynamic value "Cart_Icon_Number"
    And I click on "Dress_Page"
    And I wait few seconds


    And I click on "Brand_Button"
    And I wait few seconds
    And I click on "Brand_Odel"
    And I wait few seconds
    And I click on "Odel_Dress2"
    And I click on "SelectSize"
    And I wait few seconds
    And I click on "Odel_Dress2_L"
    And I wait few seconds
    And I get product selling price2 on "Odel_Dress2_Price"
    And I click on "add_to_cart"
    And I wait few seconds
    And I click on "Cart_Cut_Icon"
    And I wait few seconds
    And Get dynamic value "Cart_Icon_Number"
    And I click on "Dress_Page"
    And I wait few seconds



    And I click on "Brand_Button"
    And I wait few seconds
    And I click on "Brand_Odel"
    And I wait few seconds
    And I click on "Odel_Dress3"
    And I click on "SelectSize"
    And I wait few seconds
    And I click on "Odel_Dress3_M"
    And I wait few seconds
    And I get product selling price3 on "Odel_Dress3_Price"
    And I click on "add_to_cart"
    And I wait few seconds
    And I click on "Cart_Cut_Icon"
    And I wait few seconds
    And Get dynamic value "Cart_Icon_Number"



    Examples:
      | BrandName |dress_1|size|
      | BrandName |dress_1|size|


  Scenario: Test the shopping cart is opened the subtotal, should be correctly calculated for the items chosen
    When I click on "Women_Containts"
    And I click on "Type_Casual"
    And I wait few seconds
    And I click on "Dress"
    And I click on "Brand_Button"
    And I wait few seconds
    And I click on "Brand_Odel"
    And I wait few seconds
    And I click on "Odel_Dress1"
    And I click on "SelectSize"
    And I wait few seconds
    And I click on "Odel_Dress1_XL"
    And I wait few seconds
    And I get product selling price1 on "Odel_Dress1_Price"
    And I click on "add_to_cart"
    And I wait few seconds
    And I click on "Cart_Cut_Icon"
    And I wait few seconds
    And Get dynamic value "Cart_Icon_Number"
    And I click on "Dress_Page"
    And I wait few seconds


    And I click on "Brand_Button"
    And I wait few seconds
    And I click on "Brand_Odel"
    And I wait few seconds
    And I click on "Odel_Dress2"
    And I click on "SelectSize"
    And I wait few seconds
    And I click on "Odel_Dress2_L"
    And I wait few seconds
    And I get product selling price2 on "Odel_Dress2_Price"
    And I click on "add_to_cart"
    And I wait few seconds
    And I click on "Cart_Cut_Icon"
    And I wait few seconds
    And Get dynamic value "Cart_Icon_Number"
    And I click on "Dress_Page"
    And I wait few seconds



    And I click on "Brand_Button"
    And I wait few seconds
    And I click on "Brand_Odel"
    And I wait few seconds
    And I click on "Odel_Dress3"
    And I click on "SelectSize"
    And I wait few seconds
    And I click on "Odel_Dress3_M"
    And I wait few seconds
    And I get product selling price3 on "Odel_Dress3_Price"
    And I click on "add_to_cart"
    And I wait few seconds
    And I click on "Cart_Cut_Icon"
    And I wait few seconds
    And Get dynamic value "Cart_Icon_Number"
    And I wait few seconds

    And I click on "Cart_Icon"
    And I wait few seconds
    And show variable value
    And Get dynamic value1 "Cart_Total"
    And Cart total

