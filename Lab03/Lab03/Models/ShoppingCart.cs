using System.ComponentModel.DataAnnotations;

namespace Lab03.Models
{
    public class ShoppingCart
    {
        public Book book { get; set; }
        [Range(0, 10000)]
        public int Count { get; set; }
    }
}
