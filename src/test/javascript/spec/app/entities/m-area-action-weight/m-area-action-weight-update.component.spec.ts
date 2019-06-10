/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAreaActionWeightUpdateComponent } from 'app/entities/m-area-action-weight/m-area-action-weight-update.component';
import { MAreaActionWeightService } from 'app/entities/m-area-action-weight/m-area-action-weight.service';
import { MAreaActionWeight } from 'app/shared/model/m-area-action-weight.model';

describe('Component Tests', () => {
  describe('MAreaActionWeight Management Update Component', () => {
    let comp: MAreaActionWeightUpdateComponent;
    let fixture: ComponentFixture<MAreaActionWeightUpdateComponent>;
    let service: MAreaActionWeightService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAreaActionWeightUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MAreaActionWeightUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MAreaActionWeightUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAreaActionWeightService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MAreaActionWeight(123);
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
        const entity = new MAreaActionWeight();
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
