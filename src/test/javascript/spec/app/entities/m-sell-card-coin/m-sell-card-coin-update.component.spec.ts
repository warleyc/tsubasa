/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSellCardCoinUpdateComponent } from 'app/entities/m-sell-card-coin/m-sell-card-coin-update.component';
import { MSellCardCoinService } from 'app/entities/m-sell-card-coin/m-sell-card-coin.service';
import { MSellCardCoin } from 'app/shared/model/m-sell-card-coin.model';

describe('Component Tests', () => {
  describe('MSellCardCoin Management Update Component', () => {
    let comp: MSellCardCoinUpdateComponent;
    let fixture: ComponentFixture<MSellCardCoinUpdateComponent>;
    let service: MSellCardCoinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSellCardCoinUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MSellCardCoinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MSellCardCoinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MSellCardCoinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MSellCardCoin(123);
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
        const entity = new MSellCardCoin();
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
