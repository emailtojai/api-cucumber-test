module.exports = (req, res, next) => {
    if (req.path.startsWith("/customers") && req.method === 'POST') {
        if (req.body.fname) {
            var pattern = new RegExp(/[~`@!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/); //unacceptable chars
            if (pattern.test(req.body.fname)) {
                return res.status(400).json({ error: "No special chars allowed in first name" })
            }
            if (req.body.fname.length > 15) {
                return res.status(400).json({ error: "Not allowed more than 15 chars in first name" })
            }
        } else {
            return res.status(400).json({ error: "first name must not be empty or undefined" })
        }

        if (req.body.lname) {
            var pattern = new RegExp(/[~`@!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/); //unacceptable chars
            if (pattern.test(req.body.lname)) {
                return res.status(400).json({ error: "No special chars allowed in last name" })
            }
            if (req.body.lname.length > 15) {
                return res.status(400).json({ error: "Not allowed more than 15 chars in last name" })
            }
        } else {
            return res.status(400).json({ error: "last name must not be empty or undefined" })
        }

        if (req.body.addresses && req.body.addresses.length > 0) {

            if (!req.body.addresses[0].address) {
                return res.status(400).json({ error: "Address must not be empty or undefined" })
            }
            if (!req.body.addresses[0].city) {
                return res.status(400).json({ error: "city must not be empty or undefined" })
            }
            if (!req.body.addresses[0].state) {
                return res.status(400).json({ error: "state must not be empty or undefined" })
            }
            if (req.body.addresses[0].zip) {
                if (req.body.addresses[0].zip.length > 5) {
                    return res.status(400).json({ error: "Not allowed more than 5 digits zip code" })
                }
            } else {
                return res.status(400).json({ error: "zip code must not be empty or undefined" })
            }

        } else {
            return res.status(400).json({ error: "customer must have address" })
        }

    }

    if (req.path.startsWith("/products") && req.method === 'POST') {

        //validate category name
        if (req.body.category) {
            var pattern = new RegExp(/[~`@!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/); //unacceptable chars
            if (pattern.test(req.body.category)) {
                return res.status(400).json({ error: "No special chars allowed in category" })
            }
            if (req.body.category.length > 15) {
                return res.status(400).json({ error: "Not allowed more than 15 chars in category" })
            }
        } else {
            return res.status(400).json({ error: "category must not be empty or undefined" })
        }

        //validate product name
        if (req.body.pname) {
            var pattern = new RegExp(/[~`@!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/); //unacceptable chars
            if (pattern.test(req.body.pname)) {
                return res.status(400).json({ error: "No special chars allowed in product name" })
            }
            if (req.body.pname.length > 15) {
                return res.status(400).json({ error: "Not allowed more than 15 chars in product name" })
            }
        } else {
            return res.status(400).json({ error: "product name must not be empty or undefined" })
        }

        //validate company name
        if (req.body.company) {
            var pattern = new RegExp(/[~`@!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/); //unacceptable chars
            if (pattern.test(req.body.company)) {
                return res.status(400).json({ error: "No special chars allowed in company name" })
            }
            if (req.body.company.length > 15) {
                return res.status(400).json({ error: "Not allowed more than 15 chars in company name" })
            }
        } else {
            return res.status(400).json({ error: "company name must not be empty or undefined" })
        }

        //validate price
        if (req.body.price) {
            const aPrice = req.body.price
            if (req.body.price.length > 10) {
                return res.status(400).json({ error: "Not allowed more than 10 digits price" })
            }
            if (isNaN(req.body.price)) {
                return res.status(400).json({ error: "only digits allowed in price" })
            }
        } else {
            return res.status(400).json({ error: "price must not be empty or undefined" })
        }

        //validate pricein
        if (req.body.pricein) {
            if (req.body.pricein !== "dollar") {
                return res.status(400).json({ error: "Only dollar allowed as price type" })
            }
        } else {
            return res.status(400).json({ error: "pricein must not be empty or undefined" })
        }
    }

    //////Order Validation ////////
    if (req.path.startsWith("/orders") && req.method === 'POST') {
        //validate date field
        if (req.body.date) {
            const sDate = req.body.date
            if ((new Date(sDate) == "Invalid Date") && isNaN(new Date(sDate))) {
                return res.status(400).json({ error: "Invalidate date or date format" })
            }
        } else {
            return res.status(400).json({ error: "date must not be empty or undefined" })
        }

        //validate trackingno
        if (req.body.trackingno) {
            var pattern = new RegExp(/[~`@!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/); //unacceptable chars
            if (pattern.test(req.body.trackingno)) {
                return res.status(400).json({ error: "No special chars allowed in trackingno name" })
            }
            if (req.body.trackingno.length > 15) {
                return res.status(400).json({ error: "Not allowed more than 15 chars in trackingno name" })
            }
        } else {
            return res.status(400).json({ error: "trackingno must not be empty or undefined" })
        }

        //validate price
        if (req.body.price) {
            const aPrice = req.body.price
            if (req.body.price.length > 10) {
                return res.status(400).json({ error: "Not allowed more than 10 digits price" })
            }
            if (isNaN(req.body.price)) {
                return res.status(400).json({ error: "only digits allowed in price" })
            }
        } else {
            return res.status(400).json({ error: "price must not be empty or undefined" })
        }
    }


    next()
}