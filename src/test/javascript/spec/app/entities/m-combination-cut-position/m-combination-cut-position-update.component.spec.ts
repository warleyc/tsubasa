/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCombinationCutPositionUpdateComponent } from 'app/entities/m-combination-cut-position/m-combination-cut-position-update.component';
import { MCombinationCutPositionService } from 'app/entities/m-combination-cut-position/m-combination-cut-position.service';
import { MCombinationCutPosition } from 'app/shared/model/m-combination-cut-position.model';

describe('Component Tests', () => {
  describe('MCombinationCutPosition Management Update Component', () => {
    let comp: MCombinationCutPositionUpdateComponent;
    let fixture: ComponentFixture<MCombinationCutPositionUpdateComponent>;
    let service: MCombinationCutPositionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCombinationCutPositionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCombinationCutPositionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCombinationCutPositionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCombinationCutPositionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCombinationCutPosition(123);
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
        const entity = new MCombinationCutPosition();
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
