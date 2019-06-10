/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MArousalUpdateComponent } from 'app/entities/m-arousal/m-arousal-update.component';
import { MArousalService } from 'app/entities/m-arousal/m-arousal.service';
import { MArousal } from 'app/shared/model/m-arousal.model';

describe('Component Tests', () => {
  describe('MArousal Management Update Component', () => {
    let comp: MArousalUpdateComponent;
    let fixture: ComponentFixture<MArousalUpdateComponent>;
    let service: MArousalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MArousalUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MArousalUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MArousalUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MArousalService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MArousal(123);
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
        const entity = new MArousal();
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
