/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchFormationUpdateComponent } from 'app/entities/m-match-formation/m-match-formation-update.component';
import { MMatchFormationService } from 'app/entities/m-match-formation/m-match-formation.service';
import { MMatchFormation } from 'app/shared/model/m-match-formation.model';

describe('Component Tests', () => {
  describe('MMatchFormation Management Update Component', () => {
    let comp: MMatchFormationUpdateComponent;
    let fixture: ComponentFixture<MMatchFormationUpdateComponent>;
    let service: MMatchFormationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchFormationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMatchFormationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMatchFormationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMatchFormationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMatchFormation(123);
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
        const entity = new MMatchFormation();
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
