angular.module('Fablab').config(function ($translateProvider) {
    $translateProvider.translations('en', {
        date: {
            format: {
                date: 'DD/MM/YYYY',
                datetime: 'DD/MM/YYYY hh:mm:ss',
                time: 'hh:mm:ss'
            }
        },
        general: {
            days: 'days',
            date: 'Date',
            user: 'User',
            detail: 'Detail',
            comment: 'Comment',
            form: {
                duration: 'Duration'
            }
        },
        error: {
            internal: 'Internal error',
            ajax: {
                unauthorized: 'Unauthorized',
                global: 'Ajax error'
            }
        },
        menu: {
            home: 'Home',
            users: 'Users',
            reservation: 'Reservation',
            payment: 'Payment',
            accounting: 'Accounting',
            login: 'Login',
            logout: 'Logout',
            signup: 'Signup',
            machines: 'Machineries',
            machineTypes: 'Machinery types',
            machineState: 'Machinery states',
            machineStatus: 'Machinery status',
            admin: 'Administration',
            membershipTypes: 'Membership types',
            inOut: 'In / Out',
            revision: "Revisions",
            ticketStatus: 'Ticket status',
            tickets: 'Announce a failure',
            configurations: 'Configurations',
            key: 'Admin panel',
            supplies: 'Supplies',
            supplyTypes: 'Supply types',
            purchases : 'Make a purchase'
        },
        loading: {
            title: 'Loading',
            text: 'Loading...'
        },
        button: {
            cancel: 'Cancel',
            save: 'Save',
            create: 'Create',
            export: 'Export',
            search: 'Search',
            updateMailingList: 'Update mailing list'
        },
        panel: {
            search: 'Filters'
        },
        filter: {
            from: 'From',
            to: 'To'
        },
        auth: {
            result: {
                ok: 'Welcome',
                unknownUserPassword: 'Login and/or password incorrect'
            },
            pleaseSignIn: 'Please sign in',
            email: 'Email address',
            password: 'Password',
            signiIn: 'Sign In',
            signUp: 'Sign Up',
            captcha: 'Are you human ?',
            forgotPasswordQuestion: "Forgot your password ?",
            requestNewPasswod: "Send me a new password"
        },
        user: {
            firstname: 'Firstname',
            lastname: 'Lastname',
            name: 'Name',
            email: 'Email',
            balance: 'Balance',
            membership: 'Membership',
            newpassword: 'New password',
            password: 'Password',
            phone: 'Phone',
            address: 'Address',
            rfid: 'RFID',
            groups: 'Groups',
            comment: 'comment',
            birthdate: 'Birth date',
            gender: 'Gender',
            genderEnum: {
                unknown: 'Unknown',
                male: 'Male',
                female: 'Female'
            },
            subscription: {
                subscribe: 'Subscribe',
                confirmButton: 'Confirm',
                confirmTitle: 'Confirm subscription',
                price: 'Price',
                never: {
                    myself: 'You haven\'t confirm your subription yet !',
                    user: 'The user {{user.firstname}} {{user.lastname}} has not confirm its subription yet !'
                },
                expired: {
                    myself: 'Your subscription has expired since {{epirationDate}} ({{dayLeft}} days left)',
                    user: 'The subscription of the user {{user.firstname}} {{user.lastname}} has expired since {{epirationDate}} ({{dayLeft}} days)'
                },
                ok: {
                    myself: 'Your subscription will expire on {{epirationDate}} ({{dayLeft}} days left)',
                    user: 'The subscription of the user {{user.firstname}} {{user.lastname}} will expired on {{epirationDate}} ({{dayLeft}} days left)'
                }
            },
            confirmation: {
                remove: 'Do you really want to remove this user ?'
            },
            notification: {
                saved: 'User saved',
                removed: 'User removed'
            }
        },
        reservation: {
            day: 'Day',
            start: 'Start',
            end: 'End',
            user: 'User',
            machine: 'Machinery',
            type: 'type',
            date: 'Date',
            from: 'From',
            to: 'To',
            hours: 'Hours',
            reservationForDay: 'Reservation for {{date}}',
            edit: {
                title: "Book a machinery"
            }
        },
        payment: {
            userTitle: 'User',
            usageTitle: 'Add an usage',
            paymentTitle: 'Add a payment',
            enterName: 'Enter a name',
            machine: 'Machinery',
            date: 'Date',
            time: 'Time',
            additionalCost: 'Additional cost',
            comment: 'Comment',
            directPaid: 'User paid directly',
            amount: 'Amount',
            total: 'Total',
            addPayment: 'Add payment',
            addUsage: 'Add usage',
            details: 'Details',
            history: 'History',
            balanceText: 'Balance for user <i>{{firstname}} {{lastname}}</i> : <b>{{balance}}</b>',
            confirmation: {
                historyRemove: 'Do you really want to remove this history entry ?'
            },
            notification: {
                historyRemoved: 'Historique supprimé',
                usageAdded: 'Usage added',
                paymentAdded: 'Payment added'
            }
        },
        accounting: {
            title: 'In / Out',
            today: 'Today',
            yesterday: 'Yesterday',
            thisMonth: 'This month',
            lastMonth: 'Last month',
            thisYear: 'This year',
            lastYear: 'Last year',
            summary: 'Summary',
            sell: 'Sell',
            moneyIn: 'Money in',
            delta: 'Delta',
            debit: 'Debit',
            credit: 'Credit'
        },
        machine: {
            title: 'Machinery',
            code: 'Code',
            buyPrice: 'Buy price',
            name: 'Name',
            acquisitionDate: "Acquisition date",
            machineType: 'Type',
            machineState: 'State',
            machineStatus: 'Status',
            toDoRevision: 'To-Do for revision',
            create: 'Machinery creation',
            edit: 'Edit :',
            notification: {
                saved: 'Machinery saved',
                removed: 'Machinery removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this machinery ?'
            }
        },
        machineType: {
            title: 'Machinery type',
            name: 'Name',
            technicalname: 'Technical name',
            restricted: 'Restricted ?',
            create: 'Machinery type creation',
            edit: 'Edit :',
            notification: {
                saved: 'Machinery type saved',
                removed: 'Machinery type removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this machinery type ?'
            }
        },
        machineState: {
            title: 'Machinery state',
            label: 'Label',
            create: 'Machinery state creation',
            edit: 'Edit :',
            notification: {
                saved: 'Machinery state saved',
                removed: 'Machinery state removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this machinery state ?'
            }
        },
        machineStatus: {
            title: 'Machinery status',
            label: 'Label',
            warningEdit: ' ! Warning ! If you change some value, the list red-yellow-green will not work good unless you midify the source code.',
            warningCreate: ' ! Warning ! If you add some value, the list red-yellow-green will not work good unless you midify the source code.',
            create: 'Machinery status creation',
            edit: 'Edit :',
            notification: {
                saved: 'Machinery status saved',
                removed: 'Machinery status removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this machinery status ?'
            }
        },
        membershipType: {
            title: 'Membership types',
            name: 'Name',
            duration: 'Duration',
            price: 'Price',
            create: 'Membership type creation',
            edit: 'Edit :',
            notification: {
                saved: 'Membership type saved',
                removed: 'Membership type removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this membership type ?'
            }
        },
        revision: {
            title: 'Revisions',
            revisionDate: 'Revision date',
            revisionTime: 'Revision time',
            note: 'Note',
            cost: 'Cost',
            machine: 'Machinery',
            create: 'Revision creation',
            edit: 'Edit reservation @',
            notification: {
                saved: 'Revision saved',
                removed: 'Revision removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this revision ?'
            }
        },
        ticketStatus: {
            title: 'Ticket status',
            label: 'Label',
            create: 'Ticket status creation',
            edit: 'Edit :',
            notification: {
                saved: 'Ticket status saved',
                removed: 'Ticket status removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this ticket status ?'
            }
        },
        ticket: {
            ticketTitle: 'Ticket',
            title: 'Title',
            description: 'Description',
            creationDateList: 'Creation date',
            creationDate: 'Create on',
            previsionCloseDate: 'Estimated closing date',
            closeDateList: 'Closing date',
            closeDate: 'Closed on',
            machine: 'Machinery',
            status: 'Status : ',
            creationUserList: 'Create by',
            creationUser: 'by',
            closeUserList: 'Closed by',
            closeUser: 'by',
            create: 'Ticket creation',
            edit: 'Edit :',
            closeTicket: 'Close this ticket',
            reOpenTicket: 'Re-Open this ticket',
            notification: {
                saved: 'Ticket saved',
                removed: 'Ticket removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this ticket ?'
            }
        },
        configuration: {
            title: 'Configuration',
            key: 'Key',
            value: 'Value',
            name: 'Common Name',
            create: 'Configuration creation',
            edit: 'Edit :',
            notification: {
                saved: 'Configuration saved',
                removed: 'Configuration removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this configuration ?'
            }
        },
        supply: {
            title: 'Supply',
            code: 'Code',
            label: 'Label',
            sellingPrice: 'Selling price',
            unityBuyingPrice: 'Unity buying price',
            orderAddress: 'Order address',
            supplyType: 'Supply type',
            quantityStock: 'Stock quantity',
            quantityStockModal: 'Quantity',
            initialQuantity:'Initial quantity',
            modalTitle:'Add some quantity to ',
            create: 'Supply creation',
            edit: 'Edit :',
            notification: {
                saved: 'Supply saved',
                removed: 'Supply removed',
                addQuantity:'Quantity added'
            },
            confirmation: {
                remove: 'Do you really want to remove this supply ?'
            }
        },
        supplyType: {
            title: 'Supply type',
            label: 'Label',
            create: 'Supply type creation',
            edit: 'Edit :',
            notification: {
                saved: 'Supply type saved',
                removed: 'Supply type removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this supply type ?'
            }
        },
        purchase: {
            title: 'Purchase',
            purchaseDate: 'Purchase date',
            quantity: 'Quantity',
            purchasePrice: 'Purchase price',
            supply: 'Supply',
            user: 'User',
            create: 'Purchase creation',
            edit: 'Edit :',
            notification: {
                saved: 'Purchase saved',
                removed: 'Purchase removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this purchase ?'
            }
        }
    });
});
