import java.util.List;


class VolumeInfo {
            String title;
            String[] authors;
            String publisher;
            String publishedDate;
            String description;
            int pageCount;
            String[] categories;
        }

        class Item {
            VolumeInfo volumeInfo;
        }

        class GoogleBooksResponse {
            List<Item> items;
        }

