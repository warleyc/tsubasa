/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTradeSignUpdateComponent } from 'app/entities/m-gacha-rendition-trade-sign/m-gacha-rendition-trade-sign-update.component';
import { MGachaRenditionTradeSignService } from 'app/entities/m-gacha-rendition-trade-sign/m-gacha-rendition-trade-sign.service';
import { MGachaRenditionTradeSign } from 'app/shared/model/m-gacha-rendition-trade-sign.model';

describe('Component Tests', () => {
  describe('MGachaRenditionTradeSign Management Update Component', () => {
    let comp: MGachaRenditionTradeSignUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionTradeSignUpdateComponent>;
    let service: MGachaRenditionTradeSignService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTradeSignUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionTradeSignUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionTradeSignUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionTradeSignService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionTradeSign(123);
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
        const entity = new MGachaRenditionTradeSign();
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
