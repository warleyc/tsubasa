/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEncountersCommandCompatibilityUpdateComponent } from 'app/entities/m-encounters-command-compatibility/m-encounters-command-compatibility-update.component';
import { MEncountersCommandCompatibilityService } from 'app/entities/m-encounters-command-compatibility/m-encounters-command-compatibility.service';
import { MEncountersCommandCompatibility } from 'app/shared/model/m-encounters-command-compatibility.model';

describe('Component Tests', () => {
  describe('MEncountersCommandCompatibility Management Update Component', () => {
    let comp: MEncountersCommandCompatibilityUpdateComponent;
    let fixture: ComponentFixture<MEncountersCommandCompatibilityUpdateComponent>;
    let service: MEncountersCommandCompatibilityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEncountersCommandCompatibilityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MEncountersCommandCompatibilityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MEncountersCommandCompatibilityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEncountersCommandCompatibilityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MEncountersCommandCompatibility(123);
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
        const entity = new MEncountersCommandCompatibility();
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
