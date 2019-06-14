/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTriggerEffectRangeUpdateComponent } from 'app/entities/m-trigger-effect-range/m-trigger-effect-range-update.component';
import { MTriggerEffectRangeService } from 'app/entities/m-trigger-effect-range/m-trigger-effect-range.service';
import { MTriggerEffectRange } from 'app/shared/model/m-trigger-effect-range.model';

describe('Component Tests', () => {
  describe('MTriggerEffectRange Management Update Component', () => {
    let comp: MTriggerEffectRangeUpdateComponent;
    let fixture: ComponentFixture<MTriggerEffectRangeUpdateComponent>;
    let service: MTriggerEffectRangeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTriggerEffectRangeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTriggerEffectRangeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTriggerEffectRangeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTriggerEffectRangeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTriggerEffectRange(123);
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
        const entity = new MTriggerEffectRange();
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
