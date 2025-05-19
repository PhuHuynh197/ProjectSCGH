namespace Lab03.Models
{
    public class BookImage
    {
        public string Id { get; set; }
        public string Url { get; set; }
        public int BookId { get; set; }
        public Book? Book { get; set; }
    }
}
