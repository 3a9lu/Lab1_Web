data class Contact(val name: String, val phoneNumber: String)

class MobilePhone {
    private val Contacts = ArrayList<Contact>()

    fun addNewContact(contact: Contact): Boolean {
        if (findContact(contact) >= 0) {
            println("Такой контакт уже есть.")
            return false
        }
        Contacts.add(contact)
        return true
    }

    fun updateContact(oldContact: Contact, newContact: Contact): Boolean {
        val i = findContact(oldContact)
        if (i < 0) {
            println("Такого контакта нет.")
            return false
        }

        val name = Contacts[i].name
        Contacts[i] = newContact
        println("Контакт \"$name\" обновлён.")
        return true
    }

    fun removeContact(contact: Contact): Boolean {
        val i = findContact(contact)
        if (i < 0) {
            println("Такого контакта нет.")
            return false
        }
        val name = Contacts[i].name
        Contacts.removeAt(i)
        println("Контакт \"$name\" удалён.")
        return true
    }

    private fun findContact(contact: Contact): Int {
        return Contacts.indexOf(contact)
    }

    fun queryContact(name: String): Contact? {
        for (i in Contacts.indices) {
            val contact = Contacts[i]
            if (contact.name == name) {
                println("Контакт \"$name\" найден:")
                println("${Contacts[i].name} - ${Contacts[i].phoneNumber}")
                return contact
            }
        }
        println("Контакта \"$name\" нет")
        return null
    }

    fun printContacts() {
        println("Список контактов:")
        for (i in Contacts.indices) {
            println("${i + 1}) ${Contacts[i].name} - ${Contacts[i].phoneNumber}")
        }
    }
}

fun main() {
    val phone = MobilePhone()

    phone.addNewContact(Contact("Роман", "88005553535"))
    phone.addNewContact(Contact("Иван", "88005553536"))
    phone.addNewContact(Contact("Сергей", "88005553537"))
    phone.printContacts()
    println()

    phone.updateContact(Contact("Иван", "88005553536"), Contact("Иван", "88000000000"))
    println()
    phone.printContacts()
    println()

    phone.removeContact(Contact("Сергей", "88005553537"))
    println()
    phone.printContacts()
    println()

    phone.queryContact("Роман")
}