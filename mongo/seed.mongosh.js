// Run: mongosh "mongodb://localhost:27017/inventory" mongo/seed.mongosh.js
// Hyphenated collection name must use getCollection(...) in mongosh.

db.getCollection('inventory-items').insertMany([
  {
    _id: ObjectId('507f1f77bcf86cd799439011'),
    sku: 'SKU-1001',
    name: 'Sample bolts',
    quantity: 100,
    unitPrice: NumberDecimal('19.99'),
    createdAt: ISODate('2026-01-01T10:00:00.000Z'),
    updatedAt: ISODate('2026-01-01T10:00:00.000Z')
  },
  {
    _id: ObjectId('507f1f77bcf86cd799439012'),
    sku: 'SKU-2002',
    name: 'Steel plates (demo)',
    quantity: 25,
    unitPrice: NumberDecimal('45.00'),
    createdAt: ISODate('2026-01-02T14:30:00.000Z'),
    updatedAt: ISODate('2026-01-02T14:30:00.000Z')
  },
  {
    _id: ObjectId('507f1f77bcf86cd799439013'),
    sku: 'SKU-3003',
    name: 'Copper wire spool',
    quantity: 15,
    unitPrice: NumberDecimal('89.00'),
    createdAt: ISODate('2026-01-03T09:15:00.000Z'),
    updatedAt: ISODate('2026-01-03T09:15:00.000Z')
  }
]);
