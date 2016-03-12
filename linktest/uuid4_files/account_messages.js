
var ACCOUNT_MESSAGES = [
	{
		name : "w.vday16",
		message : "Pandora One. No Ads. All Romance.",
		href: "/one",
		containerClass : "vday2016"
	},
	{
		name : "w.xmasgift15",
		message : "Still have presents to buy? Get Pandora One!",
		href: "https://www.pandora.com/one/gift",
		containerClass : "xmasgift2015"
	},
	{
		name : "w.xmasadfree15",
		message : "Go ad-free this holiday season",
		href: "/one",
		containerClass : "xmasadfree2015"
	},
	{
		name : "w.dec15",
		message : "Give the gift of Pandora One",
		href: "https://www.pandora.com/one/gift",
		containerClass : "dec2015"
	},
	{
		name : "w.thanksgiving15",
		message : "Enjoy Thanksgiving ad-free",
		href : "/one",
		containerClass : "thanksgiving2015"
	},
	{
		name : "w.halloween15",
		message : "Treat yourself to ad-free listening this Halloween",
		href: "/one",
		containerClass : "halloween2015"
	},
  {
    name : "w.adfree2015",
    message : "Let's Celebrate. Zero Ads, All Music, All Day. Tune in Tomorrow.",
    href: "http://pdora.co/LLDay",
    containerClass : "adfree2015"
  },
  {
    name : "w.lateSummer2015",
    message : "Don't let the summer hits stop",
    href: "/one",
    containerClass : "lateSummer2015"
  },
  {
    name : "w.july4thp1",
    message : "Be the master of the grill and the music with ad free Pandora One",
    href: "/one",
    containerClass : "july4thp1"
  },

  {
    name : "w.summer15p1",
    message : "Don't let anything interrupt your summer fun.",
    href: "/one",
    containerClass : "summer15p1"
  },

  {
    name : "w.memorial15p1",
    message : "It's time to kick back, press play and enjoy an ad free long weekend.",
    href: "/one",
    containerClass : "memorial15p1"
  },

  {
    name : "w.mother15p1",
    message : "She was your first DJ. Thank her this Mother's Day with ad free Pandora One.",
    href: "http://www.pandora.com/one/gift",
    containerClass : "mother15p1"
  },

  {
    name : "w.cinco15p1",
    message : "Celebrate Cinco with family, friends and ad free Pandora One.",
    href: "/one",
    containerClass : "cinco15p1"
  },

  {
    name : "w.stpats15",
    message : "Your St. Patrick's Day soundtrack is here",
    action : function() {
      Pandora.launchStationFromMusicId('G769');
    },
    containerClass : "stpats15"
  },

  {
    name : "w.stpats15p1",
    message : "May you always hear your music ad free this St. Paddy's Day.",
    href: "/one",
    containerClass : "stpats15p1"
  },

	{
		name : "w.valentine15p1",
		message : "Romance is better without commercial breaks. Upgrade to ad free Pandora One.",
		href: "/one",
		containerClass : "valentine15p1"
	},

	{
		name : "w.grammy15",
		message : "Catch up on the year's best with 2015 Music Award Nominees Radio",
		action : function() {
			Pandora.launchStationFromMusicId('G1129');
		},
		containerClass : "grammy15"
	},

	{
		name : "w.workout15",
		message : "Get back on track for the new year.",
		action : function() {
			Pandora.launchGenrePanel('workout');
		},
		containerClass : "workout15"
	},

	{
		name : "w.nyep114",
		message : "One of the easiest resolutions you can make - Listen ad free with Pandora One in 2015",
		href: "/one",
		containerClass : "nyep114"
	},

	{
		name : "w.holidayp1g14v2",
		message : "Still looking for the perfect gift? Gift ad free Pandora One",
		href: "https://www.pandora.com/one/gift",
		containerClass : "holidayp1g14"
	},

	{
		name : "w.endofyear14",
		message : "2014 was another year of great music. We made some stations with the songs our listeners loved most.",
		action : function() {
			Pandora.launchGenrePanel('end_of_year');
		},
		containerClass : "endofyear14"
	},

	{
		name : "w.p1annualsunrise",
		message : "The annual plan is back just in time for the holidays. Includes 1 month FREE!",
		href: "/one",
		containerClass : "p1annualsunrise"
	},

	{
		name : "w.holidayp114",
		message : "The weather may be frightful but ad free Pandora One is so delightful.",
		href: "/one",
		containerClass : "holidayp114"
	},

	{
		name : "w.holidayp1g14",
		message : "Gift Ad free Pandora One - Share the Magic this Holiday Season",
		href: "https://www.pandora.com/one/gift",
		containerClass : "holidayp1g14"
	},

	{
		name : "w.holiday14",
		message : "Get in the spirit with all of your favorite holiday classics and more.",
		action : function() {
			Pandora.launchGenrePanel('holiday');
		},
		containerClass : "holiday14"
	},

	{
		name : "w.dinnerparty14",
		message : "You're busy enough planning dinner. Let us take care of the music.",
		action : function() {
			Pandora.launchGenrePanel('dinnercooking');
		},
		containerClass : "dinnerparty14"
	},

	{
		name : "w.p1halloween14",
		message : "This Halloween, don't let ads spook you",
		href: "/one",
		containerClass : "p1halloween14"
	},

	{
		name : "w.halloween14",
		message : "No matter what you have planned this Halloween, the perfect soundtrack awaits you",
		action : function() {
			Pandora.launchGenrePanel('halloween');
		},
		containerClass : "halloween14"
	},

	{
		name : "ios.m.p1summer14",
		message : "Ad Free listening:\nThe perfect summer accessory.",
		href: "pandorav2:/one",
		containerClass : "p1summer2014",
		type: "mobile",
		platform: "ios"
	},

	{
		name : "w.p1summer14",
		message : "Ad Free listening: the perfect summer accessory.",
		href: "/payment",
		containerClass : "p1summer2014"
	},

	{
		name : "m.p1summer14",
		message : "Ad Free listening:\nThe perfect summer accessory.",
		href: "pandorav2:/one",
		containerClass : "p1summer2014",
		type: "mobile"
	},

	{
		name : "w.comedy14",
		message : "Tired of pranks today? One of our comedy stations is sure to make you smile.",
		action : function() {
			Pandora.launchGenrePanel('comedy');
		},
		containerClass : "comedy2013"
	},

	{
		name : "w.stpats14",
		message : "Celebrate St. Patrick's Day with festive music",
		action : function() {
			Pandora.launchGenrePanel('st_patricks_day');
		},
		containerClass : "stpats2014"
	},

	{
		name : "m.valentine2014",
		message : "Listen to our Valentine's Day stations",
		action : function() {
			Stage.launchGenrePanel({'genreName':"Love Songs/Valentine's"}, null);
		},
		containerClass : "valentine2014",
		type: "mobile"
	},

	{
		name : "w.valentine2014",
		message : "Music you'll love this Valentine's Day",
		action : function() {
			Pandora.launchGenrePanel('love-songsvalentines');
		},
		containerClass : "valentine2014"
	},

	{
		name : "w.valentine2014s",
		message : "Give someone you love the gift of Pandora One",
		href : "http://www.pandora.com/one/gift",
		containerClass : "valentine2014s"
	},

	{
		name : "w.nye2013",
		message : "Find the perfect station to ring in the New Year",
		action : function() {
			Pandora.launchGenrePanel('end_of_year');
		},
		containerClass : "nye2013"
	},

	{
		name : "w.holidayp113",
		message : "Give the gift of Pandora One this holiday season",
		href : "http://www.pandora.com/one/gift",
		containerClass : "holidayp12013"
	},

	{
		name : "w.holiday13",
		message : "Celebrate the season with your favorite holiday music",
		action : function() {
			Pandora.launchGenrePanel('Holiday');
		},
		containerClass : "holiday2013"
	},

	{
		name : "w.halloween13",
		message : "Get in the Halloween Spirit with Our New Spooky Stations",
		action : function() {
			Pandora.launchGenrePanel('Halloween');
		},
		containerClass : "halloween2013"
	},

	{
		name : "m.summer13",
		message : "Enjoy the sounds of summer.",
		action : function() {
			Stage.launchGenrePanel({'genreName':'Summer'}, null);
		},
		containerClass : "summer2013",
		type: "mobile"
	},

	{
		name : "w.summer13",
		message : "Take a break and enjoy the sounds of summer.",
		action : function() {
			Pandora.launchGenrePanel('summer');
		},
		containerClass : "summer2013"
	},

	{
		name : "m.comedy13",
		message : "Laugh with our comedy stations",
		action : function() {
			Stage.launchGenrePanel({'genreName':'Comedy'}, null);
		},
		containerClass : "comedy2013",
		type: "mobile"
	},

	{
		name : "w.comedy13",
		message : "Tired of pranks today? One of our comedy stations is sure to make you smile.",
		action : function() {
			Pandora.launchGenrePanel('comedy');
		},
		containerClass : "comedy2013"
	},

	{
		name : "m.stpats13",
		message : "Check out our St. Patty's Day stations",
		action : function() {
			Stage.launchGenrePanel({'genreName':'St. Patrick\'s Day'}, null);
		},
		containerClass : "stpats2013",
		type: "mobile"
	},

	{
		name : "w.stpats13",
		message : "Get in the Irish spirit with our St. Patty's Day stations",
		action : function() {
			Pandora.launchGenrePanel('st_patricks_day');
		},
		containerClass : "stpats2013"
	},

	{
		name : "mobile.valentine2013",
		message : "Listen to our Valentine's Day Stations",
		action : function() {
			Stage.launchGenrePanel({'genreName':"valentine's"}, null);
		},
		containerClass : "valentine2013",
		type: "mobile"
	},

	{
		name : "web.valentine2013",
		message : "Check out our Valentine's Day Stations",
		action : function() {
			Pandora.launchGenrePanel('valentines');
		},
		containerClass : "valentine2013"
	},

    {
        name : "invalid_demographic_info",
        message : "Oops! We noticed your account information is inaccurate.",
        href : "/account/info",
        containerClass : "invalid_demographic_info"
    },

    {
        name : "w.invalid_demographic_info",
        message : "Oops! We noticed your account information is inaccurate.",
        href : "/account/info",
        containerClass : "invalid_demographic_info"
    },

    {
        name : "m.invalid_demographic_info",
        message : "Oops! We noticed your account information is inaccurate.",
        href : "pandorav5:/account/info",
        containerClass : "invalid_demographic_info"
    },

    {
        name : "invalid_email",
        message : "We noticed your email is invalid.",
        href : "/account/info",
        containerClass : "invalid_email"
    },

    {
        name : "w.invalid_email",
        message : "We noticed your email is invalid.",
        href : "/account/info",
        containerClass : "invalid_email"
    },

    {
        name : "m.invalid_email",
        message : "We noticed your email is invalid.",
        href : "pandorav5:/account/info",
        containerClass : "invalid_email"
    },

    {
        name : "invalid_zip_code",
        message : "We noticed your ZIP code may be out of date.",
        href : "/account/info",
        containerClass : "invalid_zip_code"
    },

    {
        name : "w.invalid_zip_code",
        message : "We noticed your ZIP code may be out of date.",
        href : "/account/info",
        containerClass : "invalid_zip_code"
    },

    {
        name : "m.invalid_zip_code",
        message : "We noticed your ZIP code may be out of date.",
        href : "pandorav5:/account/info",
        containerClass : "invalid_zip_code"
    },

    {
        name : "update_demographic_info",
        message : "Please confirm your account information.",
        href : "/account/info",
        containerClass : "update_demographic_info"
    },

     {
        name : "w.update_demographic_info",
        message : "Please confirm your account information.",
        href : "/account/info",
        containerClass : "update_demographic_info"
    },

     {
        name : "m.update_demographic_info",
        message : "Please confirm your account information.",
        href : "pandorav5:/account/info",
        containerClass : "update_demographic_info"
    }
];

