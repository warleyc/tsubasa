/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MNationalityDetailComponent } from 'app/entities/m-nationality/m-nationality-detail.component';
import { MNationality } from 'app/shared/model/m-nationality.model';

describe('Component Tests', () => {
  describe('MNationality Management Detail Component', () => {
    let comp: MNationalityDetailComponent;
    let fixture: ComponentFixture<MNationalityDetailComponent>;
    const route = ({ data: of({ mNationality: new MNationality(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNationalityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MNationalityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MNationalityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mNationality).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
