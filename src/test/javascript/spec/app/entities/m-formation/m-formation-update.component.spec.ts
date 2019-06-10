/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MFormationUpdateComponent } from 'app/entities/m-formation/m-formation-update.component';
import { MFormationService } from 'app/entities/m-formation/m-formation.service';
import { MFormation } from 'app/shared/model/m-formation.model';

describe('Component Tests', () => {
  describe('MFormation Management Update Component', () => {
    let comp: MFormationUpdateComponent;
    let fixture: ComponentFixture<MFormationUpdateComponent>;
    let service: MFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MFormationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MFormationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MFormationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MFormationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MFormation(123);
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
        const entity = new MFormation();
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
