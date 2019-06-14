/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSoundBankEventUpdateComponent } from 'app/entities/m-sound-bank-event/m-sound-bank-event-update.component';
import { MSoundBankEventService } from 'app/entities/m-sound-bank-event/m-sound-bank-event.service';
import { MSoundBankEvent } from 'app/shared/model/m-sound-bank-event.model';

describe('Component Tests', () => {
  describe('MSoundBankEvent Management Update Component', () => {
    let comp: MSoundBankEventUpdateComponent;
    let fixture: ComponentFixture<MSoundBankEventUpdateComponent>;
    let service: MSoundBankEventService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSoundBankEventUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MSoundBankEventUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MSoundBankEventUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MSoundBankEventService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MSoundBankEvent(123);
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
        const entity = new MSoundBankEvent();
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
