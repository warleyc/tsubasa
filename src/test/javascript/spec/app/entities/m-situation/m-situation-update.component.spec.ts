/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSituationUpdateComponent } from 'app/entities/m-situation/m-situation-update.component';
import { MSituationService } from 'app/entities/m-situation/m-situation.service';
import { MSituation } from 'app/shared/model/m-situation.model';

describe('Component Tests', () => {
  describe('MSituation Management Update Component', () => {
    let comp: MSituationUpdateComponent;
    let fixture: ComponentFixture<MSituationUpdateComponent>;
    let service: MSituationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSituationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MSituationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MSituationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MSituationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MSituation(123);
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
        const entity = new MSituation();
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
