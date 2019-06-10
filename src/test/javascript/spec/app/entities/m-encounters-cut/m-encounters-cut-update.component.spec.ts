/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEncountersCutUpdateComponent } from 'app/entities/m-encounters-cut/m-encounters-cut-update.component';
import { MEncountersCutService } from 'app/entities/m-encounters-cut/m-encounters-cut.service';
import { MEncountersCut } from 'app/shared/model/m-encounters-cut.model';

describe('Component Tests', () => {
  describe('MEncountersCut Management Update Component', () => {
    let comp: MEncountersCutUpdateComponent;
    let fixture: ComponentFixture<MEncountersCutUpdateComponent>;
    let service: MEncountersCutService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEncountersCutUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MEncountersCutUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MEncountersCutUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEncountersCutService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MEncountersCut(123);
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
        const entity = new MEncountersCut();
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
