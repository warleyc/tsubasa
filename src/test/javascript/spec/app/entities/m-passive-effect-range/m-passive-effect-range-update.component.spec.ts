/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPassiveEffectRangeUpdateComponent } from 'app/entities/m-passive-effect-range/m-passive-effect-range-update.component';
import { MPassiveEffectRangeService } from 'app/entities/m-passive-effect-range/m-passive-effect-range.service';
import { MPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';

describe('Component Tests', () => {
  describe('MPassiveEffectRange Management Update Component', () => {
    let comp: MPassiveEffectRangeUpdateComponent;
    let fixture: ComponentFixture<MPassiveEffectRangeUpdateComponent>;
    let service: MPassiveEffectRangeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPassiveEffectRangeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPassiveEffectRangeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPassiveEffectRangeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPassiveEffectRangeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPassiveEffectRange(123);
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
        const entity = new MPassiveEffectRange();
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
