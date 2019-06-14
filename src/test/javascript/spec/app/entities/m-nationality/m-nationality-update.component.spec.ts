/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MNationalityUpdateComponent } from 'app/entities/m-nationality/m-nationality-update.component';
import { MNationalityService } from 'app/entities/m-nationality/m-nationality.service';
import { MNationality } from 'app/shared/model/m-nationality.model';

describe('Component Tests', () => {
  describe('MNationality Management Update Component', () => {
    let comp: MNationalityUpdateComponent;
    let fixture: ComponentFixture<MNationalityUpdateComponent>;
    let service: MNationalityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNationalityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MNationalityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MNationalityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MNationalityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MNationality(123);
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
        const entity = new MNationality();
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
