/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDeckConditionUpdateComponent } from 'app/entities/m-deck-condition/m-deck-condition-update.component';
import { MDeckConditionService } from 'app/entities/m-deck-condition/m-deck-condition.service';
import { MDeckCondition } from 'app/shared/model/m-deck-condition.model';

describe('Component Tests', () => {
  describe('MDeckCondition Management Update Component', () => {
    let comp: MDeckConditionUpdateComponent;
    let fixture: ComponentFixture<MDeckConditionUpdateComponent>;
    let service: MDeckConditionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDeckConditionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDeckConditionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDeckConditionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDeckConditionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDeckCondition(123);
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
        const entity = new MDeckCondition();
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
