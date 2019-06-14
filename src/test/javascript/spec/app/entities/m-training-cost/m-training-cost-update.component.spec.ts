/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTrainingCostUpdateComponent } from 'app/entities/m-training-cost/m-training-cost-update.component';
import { MTrainingCostService } from 'app/entities/m-training-cost/m-training-cost.service';
import { MTrainingCost } from 'app/shared/model/m-training-cost.model';

describe('Component Tests', () => {
  describe('MTrainingCost Management Update Component', () => {
    let comp: MTrainingCostUpdateComponent;
    let fixture: ComponentFixture<MTrainingCostUpdateComponent>;
    let service: MTrainingCostService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTrainingCostUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTrainingCostUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTrainingCostUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTrainingCostService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTrainingCost(123);
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
        const entity = new MTrainingCost();
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
