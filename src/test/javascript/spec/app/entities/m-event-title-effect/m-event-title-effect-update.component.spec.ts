/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEventTitleEffectUpdateComponent } from 'app/entities/m-event-title-effect/m-event-title-effect-update.component';
import { MEventTitleEffectService } from 'app/entities/m-event-title-effect/m-event-title-effect.service';
import { MEventTitleEffect } from 'app/shared/model/m-event-title-effect.model';

describe('Component Tests', () => {
  describe('MEventTitleEffect Management Update Component', () => {
    let comp: MEventTitleEffectUpdateComponent;
    let fixture: ComponentFixture<MEventTitleEffectUpdateComponent>;
    let service: MEventTitleEffectService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEventTitleEffectUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MEventTitleEffectUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MEventTitleEffectUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEventTitleEffectService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MEventTitleEffect(123);
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
        const entity = new MEventTitleEffect();
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
