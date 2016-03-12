
// this is the list of available images for the gift choices
// drop new ones in here and static push them out

// small_image = the image that appears in the scroller
// selected_image = the image that appears in the current selection
// lightbox_image = the image that appears in the example lightbox box
// print_image = the image that gets printed
// email_image = the image that gets emailed
// enabled - whether this image appears in the current set of choices -
// this flag allows us to keep halloween / seasonal images in the set,
// and have them still render properly later on, but without displaying
// them in the card catalog

var giftChoiceList = [

	
	{
        name : "holiday2",
        title : "Holiday",
        small_image : "/static/images/p1_gift/choices/image22_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image22_selected.png",
        lightbox_image_print : "/static/images/p1_gift/choices/image22_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image22_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image22_print.jpg",
        email_image : "/static/images/p1_gift/choices/image22_email.jpg",
        email_bg_color: "#e2e9f1",
        enabled: "true"
    },
	
	{
        name : "holiday3",
        title : "Holiday",
        small_image : "/static/images/p1_gift/choices/image23_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image23_selected.png",
        lightbox_image_print : "/static/images/p1_gift/choices/image23_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image23_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image23_print.jpg",
        email_image : "/static/images/p1_gift/choices/image23_email.jpg",
        email_bg_color: "#e2e9f1",
        enabled: "true"
    },
	
	{
        name : "holiday4",
        title : "Holiday",
        small_image : "/static/images/p1_gift/choices/image24_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image24_selected.png",
        lightbox_image_print : "/static/images/p1_gift/choices/image24_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image24_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image24_print.jpg",
        email_image : "/static/images/p1_gift/choices/image24_email.jpg",
        email_bg_color: "#e2e9f1",
        enabled: "true"
    },
	
	{
        name : "holiday1",
        title : "Holiday",
        small_image : "/static/images/p1_gift/choices/image21_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image21_selected.png",
        lightbox_image_print : "/static/images/p1_gift/choices/image21_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image21_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image21_print.jpg",
        email_image : "/static/images/p1_gift/choices/image21_email.jpg",
        email_bg_color: "#e2e9f1",
        enabled: "true"
    },

	{
        name : "holiday5",
        title : "Holiday",
        small_image : "/static/images/p1_gift/choices/image20_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image20_selected.png",
        lightbox_image_print : "/static/images/p1_gift/choices/image20_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image20_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image20_print.jpg",
        email_image : "/static/images/p1_gift/choices/image20_email.jpg",
        email_bg_color: "#e2e9f1",
        enabled: "true"
    },

	{
        name : "generic",
        title : "Generic",
        small_image : "/static/images/p1_gift/choices/image1_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image1_selected.png",
        lightbox_image_print : "/static/images/p1_gift/choices/image1_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image1_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image1_print.jpg",
        email_image : "/static/images/p1_gift/choices/image1_email.jpg",
        email_bg_color: "#e2e9f1",
        enabled: "true"
    },

    {
        name : "birthday1",
        title : "Happy Birthday",
        small_image : "/static/images/p1_gift/choices/image2_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image2_selected.jpg",
        lightbox_image_print : "/static/images/p1_gift/choices/image2_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image2_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image2_print.jpg",
        email_image : "/static/images/p1_gift/choices/image2_email.jpg",
        email_bg_color: "#d6e6e8",
        enabled: "true"
    },

    {
        name : "birthday2",
        title : "Happy Birthday",
        small_image : "/static/images/p1_gift/choices/image3_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image3_selected.jpg",
        lightbox_image_print : "/static/images/p1_gift/choices/image3_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image3_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image3_print.jpg",
        email_image : "/static/images/p1_gift/choices/image3_email.jpg",
        email_bg_color: "#d9ebec",
        enabled: "true"
    },

    {
        name : "thankyou1",
        title : "Thank You",
        small_image : "/static/images/p1_gift/choices/image4_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image4_selected.jpg",
        lightbox_image_print : "/static/images/p1_gift/choices/image4_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image4_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image4_print.jpg",
        email_image : "/static/images/p1_gift/choices/image4_email.jpg",
        email_bg_color: "#eaeef2",
        enabled: "true"
    },

    {
        name : "thankyou2",
        title : "Thank You",
        small_image : "/static/images/p1_gift/choices/image5_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image5_selected.jpg",
        lightbox_image_print : "/static/images/p1_gift/choices/image5_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image5_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image5_print.jpg",
        email_image : "/static/images/p1_gift/choices/image5_email.jpg",
        email_bg_color: "#eaeff8",
        enabled: "true"
    },

    {
        name : "gift1",
        title : "A Gift For You",
        small_image : "/static/images/p1_gift/choices/image6_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image6_selected.jpg",
        lightbox_image_print : "/static/images/p1_gift/choices/image6_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image6_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image6_print.jpg",
        email_image : "/static/images/p1_gift/choices/image6_email.jpg",
        email_bg_color: "#eae7ea",
        enabled: "true"
    },

      {
        name : "valentines1",
        title : "Valentine's Day",
        small_image : "/static/images/p1_gift/choices/image10_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image10_selected.png",
        lightbox_image_print : "/static/images/p1_gift/choices/image10_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image10_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image10_print.jpg",
        email_image : "/static/images/p1_gift/choices/image10_email.jpg",
        email_bg_color: "#e4e3e3",
        enabled: "false"
    },

      {
        name : "valentines2",
        title : "Valentine's Day",
        small_image : "/static/images/p1_gift/choices/image11_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image11_selected.png",
        lightbox_image_print : "/static/images/p1_gift/choices/image11_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image11_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image11_print.jpg",
        email_image : "/static/images/p1_gift/choices/image11_email.jpg",
        email_bg_color: "#ece8e7",
        enabled: "false"
    },

      {
        name : "valentines3",
        title : "Valentine's Day",
        small_image : "/static/images/p1_gift/choices/image12_thumbnail.jpg",
        selected_image : "/static/images/p1_gift/choices/image12_selected.png",
        lightbox_image_print : "/static/images/p1_gift/choices/image12_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image12_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image12_print.jpg",
        email_image : "/static/images/p1_gift/choices/image12_email.jpg",
        email_bg_color: "#ececec",
        enabled: "false"
    },

      {
        name : "mothersday",
        title : "Mother's Day",
        small_image : "/static/images/p1_gift/choices/image13_thumbnail.png",
        selected_image : "/static/images/p1_gift/choices/image13_selected.png",
        lightbox_image_print : "/static/images/p1_gift/choices/image13_lightbox_print.jpg",
        lightbox_image_email : "/static/images/p1_gift/choices/image13_lightbox_email.png",
        print_image : "/static/images/p1_gift/choices/image13_print.jpg",
        email_image : "/static/images/p1_gift/choices/image13_email.jpg",
        email_bg_color: "#e2e9f1",
        enabled: "false"
    }


];



