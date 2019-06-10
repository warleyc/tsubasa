/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MExtensionSaleUpdateComponent } from 'app/entities/m-extension-sale/m-extension-sale-update.component';
import { MExtensionSaleService } from 'app/entities/m-extension-sale/m-extension-sale.service';
import { MExtensionSale } from 'app/shared/model/m-extension-sale.model';

describe('Component Tests', () => {
  describe('MExtensionSale Management Update Component', () => {
    let comp: MExtensionSaleUpdateComponent;
    let fixture: ComponentFixture<MExtensionSaleUpdateComponent>;
    let service: MExtensionSaleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MExtensionSaleUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MExtensionSaleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MExtensionSaleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MExtensionSaleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MExtensionSale(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MExtensionSale();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
