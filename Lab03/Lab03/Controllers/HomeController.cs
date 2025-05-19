using Lab03.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace Lab03.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;
        private readonly IBookRepository _bookRepository;
        private readonly ICategoryRepository _categoryRepository;
        // Show all books
        public HomeController(IBookRepository bookRepository, ICategoryRepository categoryRepository)
        {
            _bookRepository = bookRepository;
            _categoryRepository = categoryRepository;
        }
        public async Task<IActionResult> Index()
        {
            var books = await _bookRepository.GetAllAsync();
            return View(books);
        }

        public IActionResult Privacy()
        {
            return View();
        }
        public IActionResult Book()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
        public async Task<IActionResult> Detail(int id)
        {
            ShoppingCart cartObj = new()
            {
                book = await _bookRepository.GetByIdAsync(id)
            };
            return View(cartObj);
        }

    }
}
