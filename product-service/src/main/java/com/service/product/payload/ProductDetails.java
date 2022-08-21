package com.service.product.payload;

import java.io.Serializable;
import java.util.List;

@lombok.Getter
@lombok.Setter
public class ProductDetails implements Serializable {
    private static final long serialVersionUID = 7133920698076397954L;
    private String id;
    private String product_type;
    private String model_number;
    private String name;
    private MetaData meta_data;
    private List<ViewList> view_list;
    private AttributeList attribute_list;
    private List<BreadcrumbList> breadcrumb_list;
    private PricingInformation pricing_information;
    private ProductDescription product_description;
    private boolean recommendationsEnabled;
    private List<ProductLinkList> product_link_list;
    private Embellishment embellishment;

    // review service fields
    private Double averageReviewScore;
    private Long numberOfReviews;

    @lombok.Getter
    @lombok.Setter
    public static class Embellishment {
        private List<EmbellishmentOption> embellishmentOptions;
        private String articleType;
    }

    @lombok.Getter
    @lombok.Setter
    public static class EmbellishmentOption{
        private String position;
        private int positionPrice;
        private boolean allowChooseOwnText;
        private List<Field> fields;
    }

    @lombok.Getter
    @lombok.Setter
    public static class Field {
        private String type;
        private String key;
        private int maxLength;
        private int minLength;
        private String validation;
        private String textColor;
        private boolean usesStock;
        private Object stockCollection;
    }

    @lombok.Getter
    @lombok.Setter
    public static class ProductLinkList {
        private String type;
        private String productId;
        private String name;
        private String url;
        private String image;
        private String altImage;
        private PricingInformation pricing_information;
        private String search_color;
        private String default_color;
        private String source;
    }

    @lombok.Getter
    @lombok.Setter
    public static class DescriptionAssets{
        private String image_url;
        private Object video_url;
        private Object poster_url;
    }

    @lombok.Getter
    @lombok.Setter
    public static class MetaData{
        private String canonical;
        private String description;
        private String keywords;
        private String page_title;
        private String site_name;
    }

    @lombok.Getter
    @lombok.Setter
    public static class Metadata2{
        private List<String> asset_usage;
        private String imageStyle;
        private String view;
        private String usageTerms;
        private String sortOrder;
        private List<Object> subjects;
    }

    @lombok.Getter
    @lombok.Setter
    public static class ViewList{
        private String type;
        private String source;
        private String image_url;
        private Metadata2 metadata;
    }

    @lombok.Getter
    @lombok.Setter
    public static class SizeFitBar{
        private String value;
        private int markerCount;
        private int selectedMarkerIndex;
    }

    @lombok.Getter
    @lombok.Setter
    public static class SizeTypes{
        private int size;
    }

    @lombok.Getter
    @lombok.Setter
    public static class AttributeList{
        private boolean sale;
        private String brand;
        private String color;
        private String gender;
        private boolean outlet;
        private List<String> sport;
        private List<String> surface;
        private String category;
        private List<String> sportSub;
        private SizeFitBar size_fit_bar;
        private List<String> collection;
        private String search_color;
        private boolean customizable;
        private List<String> productType;
        private boolean personalizable;
        private boolean isCnCRestricted;
        private boolean mandatory_personalization;
        private String search_color_raw;
        private boolean is_orderable;
        private boolean isWaitingRoomProduct;
        private boolean isInPreview;
        private boolean specialLaunch;
        private String special_launch_type;
        private SizeTypes sizeTypes = new SizeTypes();
        private boolean is_flash;
        private String size_chart_link;
    }

    @lombok.Getter
    @lombok.Setter
    public static class BreadcrumbList{
        private String text;
        private String link;
    }

    @lombok.Getter
    @lombok.Setter
    public static class PricingInformation{
        private int currentPrice;
        private int standard_price;
        private double standard_price_no_vat;
        private int sale_price;
    }

    @lombok.Getter
    @lombok.Setter
    public static class WashCareInstructions{
        private List<Object> care_instructions;
    }

    @lombok.Getter
    @lombok.Setter
    public static class ProductDescription{
        public String title;
        public String text;
        public String subtitle;
        public List<String> usps;
        public WashCareInstructions wash_care_instructions;
        public DescriptionAssets description_assets;
    }

    @lombok.Getter
    @lombok.Setter
    public static class Callouts{
        public List<CalloutTopStack> callout_top_stack;
    }

    @lombok.Getter
    @lombok.Setter
    public static class CalloutTopStack{
        public String id;
        public String title;
        public String sub_title;
        public String body;
        public String link_text;
    }

}

